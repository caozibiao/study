package com.java.study.jvm;

import java.io.*;


public class FirstClassLoader extends ClassLoader{
    private String classPath;

    public FirstClassLoader(String classPath) {
        this.classPath = classPath;
    }

    private byte[] getByte(String name) throws Exception{
        name = name.replaceAll("\\.", "/");
        FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
        int len = fileInputStream.available();
        byte [] data = new byte[len];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte [] data = getByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    if(!name.startsWith("com.java.study")) {
                        return this.getParent().loadClass(name);
                    }
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }

    }


}
