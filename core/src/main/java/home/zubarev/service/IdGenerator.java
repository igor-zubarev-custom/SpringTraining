package home.zubarev.service;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
    private static Long id = 0L;

    public static Long generateId(){
        id = ++id;
        return id;
    }
}
