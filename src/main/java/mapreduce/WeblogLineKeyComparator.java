package mapreduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class WeblogLineKeyComparator extends WritableComparator {
	protected WeblogLineKeyComparator() {
        super(WeblogLineCompositeKeyWritable.class, true);
    }
	
	@Override
    public int compare(WritableComparable a, WritableComparable b) {
		WeblogLineCompositeKeyWritable aKey = (WeblogLineCompositeKeyWritable) a;
		WeblogLineCompositeKeyWritable bKey = (WeblogLineCompositeKeyWritable) b;
		
		int result = aKey.getClientIP().compareTo(bKey.getClientIP());
		if(result != 0) return result;
		
		long aTs = aKey.getTimeStamp();
		long bTs = bKey.getTimeStamp();
		return (aTs == bTs ? 0 : (aTs > bTs) ? 1 : -1);
	}
}
