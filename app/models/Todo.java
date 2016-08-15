package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model; 
import play.mvc.*;

@Entity
public class Todo extends Model implements QueryStringBindable<Todo> {

    @Id
    public Long id;

    public Boolean done;
    public String text;

    @Override
    public Optional<Todo> bind(String key, Map<String, String[]> data) {
        try{
            done = new Boolean(data.get("done")[0]);
            text = new String(data.get("text")[0]);
            return Optional.of(this);
        } catch (Exception e){ // no parameter match return None
            return Optional.of(null);
        }
    }

    @Override
    public String unbind(String key) {
        return new StringBuilder()
                .append("done=")
                .append(done)
                .append("&text=")
                .append(text)
                .toString();
    }

    @Override
    public String javascriptUnbind() {
        return "function(k,v) {\n" +
          "    return encodeURIComponent('done')+'='+v.done+'&'+encodeURIComponent('text')+'='+v.text;\n" +
                                                    "}";
    }

}
