package com.design_pattern;


interface MasterDataHub {
    void access();
}

class MasterDataHubImpl implements MasterDataHub {

    @Override
    public void access() {
        //master data hub connection
        System.out.println("welcome to master data hub");
    }
}

public class ProxyPatternTest implements MasterDataHub {
    private MasterDataHub masterDataHub;
    private boolean isAdmin;

    ProxyPatternTest(String userName, String password) {
        if (userName == "admin" && password == "secured") {
            masterDataHub = new MasterDataHubImpl();
            isAdmin = true;
        }
    }

    public static void main(String[] args) {
        ProxyPatternTest proxyPatternTest = new ProxyPatternTest("admin", "secured");
        proxyPatternTest.access();
    }

    @Override
    public void access() {
        if (isAdmin) {
            System.out.println("access granted");
            masterDataHub.access();
        } else {
            System.out.println("you are not allowed to access master data hub");
        }
    }
}
