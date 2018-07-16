package com.epam.chatbotserver.utility;



import com.epam.chatbotserver.models.User;
import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Simple Cache for users.Users will be deleted after 15 minutes after entry.
 */

public class UserCache {
    private static UserCache instance = new UserCache();
    private static long EXPIRED_TIME_IN_MINUTES = 15;
    private static final Map<String, byte[]> cacheMap = new PassiveExpiringMap<>(EXPIRED_TIME_IN_MINUTES,TimeUnit.MINUTES);

    private UserCache(){
    }

    public static UserCache getInstance(){
        if (instance==null){
            synchronized (UserCache.class){
                if (instance==null){
                    instance = new UserCache();
                }
            }
        }
        return instance;
    }

    private class ObjectConverter{

        ObjectConverter(){
        }

        /**
         *Converts object to byte array
         *
         * @param object
         * @return
         * @throws IOException
         */
        private byte[] convertToBytes(User object) throws IOException {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutput out = new ObjectOutputStream(bos)) {
                out.writeObject(object);
                return bos.toByteArray();
            }
        }

        /**
         * Converts byte array to object
         * @param bytes
         * @return
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private User convertFromBytes(byte[] bytes)throws IOException, ClassNotFoundException{
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                 ObjectInput in = new ObjectInputStream(bis)) {
                return (User)in.readObject();
            }
        }
    }

    /**
     * Converts user to byte array and puts in map
     * @param user
     * @throws IOException
     */
    public void putObject(User user) throws IOException {
        ObjectConverter objectConverter = new ObjectConverter();
        byte[] bytes = objectConverter.convertToBytes(user);
        cacheMap.put(user.getIdInBotSystem(),bytes);
    }

    public User getObject(String id) throws IOException, ClassNotFoundException {
        ObjectConverter objectConverter = new ObjectConverter();
        User obj = objectConverter.convertFromBytes(cacheMap.get(id));
        return obj;
    }

    public void removeObj(String id) {
        cacheMap.remove(id);
    }

    public boolean containsUser(String id){
        return cacheMap.containsKey(id);
    }

    public int getCacheSize(){return cacheMap.size();}
}
