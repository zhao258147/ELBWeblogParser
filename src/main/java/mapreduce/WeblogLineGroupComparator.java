package mapreduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class WeblogLineGroupComparator extends WritableComparator {
	public WeblogLineGroupComparator() {
		super(WeblogLineCompositeKeyWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
    	WeblogLineCompositeKeyWritable aKey = (WeblogLineCompositeKeyWritable) a;
    	WeblogLineCompositeKeyWritable bKey = (WeblogLineCompositeKeyWritable) b;
        
        return aKey.getClientIP().compareTo(bKey.getClientIP());
    }
}
