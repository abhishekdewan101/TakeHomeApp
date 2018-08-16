package abhishekdewan101.com.doordashlite.data.local;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBTypeConverters {
    @TypeConverter
    public String fromListOfStrings(List<String> tags) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < tags.size(); i++) {
            if (i == tags.size() - 1) {
                builder.append(tags.get(i));
            } else {
                builder.append(tags.get(i))
                .append("::");
            }
        }
        return builder.toString();
    }

    @TypeConverter
    public List<String> fromStringToList(String s) {
        return Arrays.asList(s.split("::"));
    }
}
