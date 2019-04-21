package com.example.xposedhook.module;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class Okhttp3 {

    public static void byPass(ClassLoader classLoader){
        /*
        *hook org.conscrypt.Platform.java里面的checkServerTrusted
         */
        try {
            Class clz = classLoader.loadClass("org.conscrypt.Platform");
            Class clz_ConscryptEngine = classLoader.loadClass("org.conscrypt.ConscryptEngine");
            Class clz_AbstractConscryptSocket = classLoader.loadClass("org.conscrypt.AbstractConscryptSocket");
            XposedHelpers.findAndHookMethod(
                    "org.conscrypt.Platform",
                    classLoader,
                    "checkServerTrusted",
                    javax.net.ssl.X509TrustManager.class,
                    java.security.cert.X509Certificate[].class,
                    String.class,
                    clz_ConscryptEngine,
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod( MethodHookParam methodHookParam ) throws Throwable {
                            return null;
                        }
                    });
            XposedHelpers.findAndHookMethod(
                    "org.conscrypt.Platform",
                    classLoader,
                    "checkServerTrusted",
                    javax.net.ssl.X509TrustManager.class,
                    java.security.cert.X509Certificate[].class,
                    String.class,
                    clz_AbstractConscryptSocket,
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod( MethodHookParam methodHookParam ) throws Throwable {
                            return null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();

        }
        /*
         *hook com.android.org.conscrypt.Platform.java里面的checkServerTrusted
         */
        try {
            Class clz = classLoader.loadClass("com.android.org.conscrypt.Platform");
            Class clz_OpenSSLEngineImpl = classLoader.loadClass("com.android.org.conscrypt.OpenSSLEngineImpl");
            Class clz_OpenSSLSocketImpl = classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
            XposedHelpers.findAndHookMethod(
                    "com.android.org.conscrypt.Platform",
                    classLoader,
                    "checkServerTrusted",
                    javax.net.ssl.X509TrustManager.class,
                    java.security.cert.X509Certificate[].class,
                    String.class,
                    clz_OpenSSLEngineImpl,
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod( MethodHookParam methodHookParam ) throws Throwable {
                            XposedBridge.log("Hook com.android.org.conscrypt.Platform.checkServerTrusted() successful");
                            return null;
                        }
                    });

            XposedHelpers.findAndHookMethod(
                    "com.android.org.conscrypt.Platform",
                    classLoader,
                    "checkServerTrusted",
                    javax.net.ssl.X509TrustManager.class,
                    java.security.cert.X509Certificate[].class,
                    String.class,
                    clz_OpenSSLSocketImpl,
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod( MethodHookParam methodHookParam ) throws Throwable {
                            XposedBridge.log("Hook com.android.org.conscrypt.Platform.checkServerTrusted() successful");
                            StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                            for(StackTraceElement e:ste){
                                XposedBridge.log(e.toString());
                            }

                            return null;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();

        }
        /**
         *
         * */
        try {
            XposedHelpers.findAndHookMethod(
                    "com.nearme.network.a",
                    classLoader,
                    "ހ",
                    classLoader.loadClass("com.nearme.network.internal.BaseRequest"),
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod( MethodHookParam param ) throws Throwable {
                            XposedBridge.log("Hook com.nearme.network.a.ހ");
                        }
                    });

            XposedHelpers.findAndHookMethod(
                    "a.a.a.all",
                    classLoader,
                    "֏",
                    classLoader.loadClass("com.nearme.network.internal.Request"),
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod( MethodHookParam param ) throws Throwable {
                            XposedBridge.log("Hook a.a.a.all.֏(Request)");
                        }
                    });
            /*
            * 此函数应该就是new OkHttp3.Builder的对象进行发送http报文的地方
            * **/
            XposedHelpers.findAndHookMethod(
                    "a.a.a.all",
                    classLoader,
                    "ؠ",
                    classLoader.loadClass("com.nearme.network.internal.Request"),
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod( MethodHookParam param ) throws Throwable {
                            XposedBridge.log("Hook a.a.a.all.ؠ(Request)");
                        }
                    });

            XposedHelpers.findAndHookMethod(
                    "a.a.a.all",
                    classLoader,
                    "֏",
                    classLoader.loadClass("javax.net.ssl.X509TrustManager"),
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod( MethodHookParam param ) throws Throwable {
                            XposedBridge.log("Hook a.a.a.all.֏(X509TrustManager)");
                        }
                    });

            XposedHelpers.findAndHookMethod(
                    "a.a.a.all",
                    classLoader,
                    "֏",
                    classLoader.loadClass("javax.net.ssl.X509TrustManager"),
                    new XC_MethodHook() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        protected void afterHookedMethod( MethodHookParam param ) throws Throwable {
//                            super.afterHookedMethod(param);
                            XposedBridge.log("Hook a.a.a.all.֏(X509TrustManager) too");
                            SSLContext v0 = SSLContext.getInstance("TLS");
                            v0.init(null,new TrustManager[]{new X509ExtendedTrustManager() {
                                @Override
                                public void checkClientTrusted( X509Certificate[] x509Certificates, String s, Socket socket ) throws CertificateException {
                                    return;
                                }

                                @Override
                                public void checkServerTrusted( X509Certificate[] x509Certificates, String s, Socket socket ) throws CertificateException {

                                }

                                @Override
                                public void checkClientTrusted( X509Certificate[] x509Certificates, String s, SSLEngine sslEngine ) throws CertificateException {

                                }

                                @Override
                                public void checkServerTrusted( X509Certificate[] x509Certificates, String s, SSLEngine sslEngine ) throws CertificateException {

                                }

                                @Override
                                public void checkClientTrusted( X509Certificate[] x509Certificates, String s ) throws CertificateException {

                                }

                                @Override
                                public void checkServerTrusted( X509Certificate[] x509Certificates, String s ) throws CertificateException {

                                }

                                @Override
                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[0];
                                }
                            }},null);
                            param.setResult(v0);
                        }
                    }
            );

        }catch (Exception e){
            e.printStackTrace();
        }
        /*
         *hook javax.net.ssl.X509TrustManager.java里面的checkServerTrusted
         */
//        try {
//            XposedHelpers.findAndHookMethod(
//                    "javax.net.ssl.X509TrustManager",
//                    classLoader,
//                    "checkServerTrusted",
//                    java.security.cert.X509Certificate[].class,
//                    String.class,
//                    new XC_MethodReplacement() {
//                        @Override
//                        protected Object replaceHookedMethod( MethodHookParam methodHookParam ) throws Throwable {
//                            XposedBridge.log("Hook javax.net.ssl.X509TrustManager.checkServerTrusted() successful");
//                            return null;
//                        }
//                    });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
}
