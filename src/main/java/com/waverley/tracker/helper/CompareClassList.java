package com.waverley.tracker.helper;

import com.waverley.tracker.model.History;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 1/22/2017.
 */
@Component
public class CompareClassList<B> {

    List<B> nb;

    public List<B> compare(List<B> al, List<B> bl){
        nb = new ArrayList<B>();
        if(al.size()==0){
            return bl;
        }
        for (B b: al) {
            for (B b2 : bl) {
                if(b.toString().equals(b2.toString())){
                    nb.add(b);
                    break;
                }
            }
        }
        return nb;
    }

}
