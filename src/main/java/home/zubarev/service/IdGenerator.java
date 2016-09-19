package home.zubarev.service;

import org.springframework.stereotype.Service;

/**
 * Created by Igor Zubarev on 13.09.2016.
 */
@Service
public class IdGenerator {
    private static Long id = 0L;

    public static Long generateId(){
        id = ++id;
        return id;
    }
}
