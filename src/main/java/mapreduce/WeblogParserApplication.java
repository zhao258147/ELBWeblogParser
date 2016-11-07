package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WeblogParserApplication extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new WeblogParserApplication(), args);
        System.exit(res);       
    }
	
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 3) {
            System.out.println("usage: [input] [output] [temp]");
            System.exit(-1);
        }

        Job job1 = Job.getInstance(new Configuration(), "weblog1");
        
        job1.setMapperClass(WeblogMapper.class);
        job1.setReducerClass(WeblogReducer.class);

        job1.setPartitionerClass(WeblogLinePartitioner.class);
        job1.setGroupingComparatorClass(WeblogLineGroupComparator.class);
        job1.setSortComparatorClass(WeblogLineKeyComparator.class);

        job1.setMapOutputKeyClass(WeblogLineCompositeKeyWritable.class);
        job1.setMapOutputValueClass(WeblogLine.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(WeblogSessionSummary.class);
        
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[2]));

        job1.setJarByClass(WeblogParserApplication.class);

        job1.waitForCompletion(true);
        
        
        Job job2 = Job.getInstance(new Configuration(), "weblog2");
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(WeblogLine.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(WeblogSessionSummary.class);

        job2.setMapperClass(WeblogMapper.class);
        job2.setReducerClass(WeblogReducer.class);

        job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job2, new Path(args[2]));
        FileOutputFormat.setOutputPath(job2, new Path(args[1]));

        job2.setJarByClass(WeblogParserApplication.class);
//        job2.waitForCompletion(true);
        return 0;
        
	}

}
