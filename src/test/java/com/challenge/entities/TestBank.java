package com.challenge.entities;

import org.junit.Test;
import service.entities.Bank;
import static org.junit.Assert.*;

public class TestBank {

    @Test
    public void testBank() throws Exception {
        System.out.println("Inside testBank().");

        Bank bank = new Bank("B001", "CaixaBank");

        assertEquals(bank, new Bank("B001", "CaixaBank"));
//        assertEquals(bank, new Bank("A001", "CaixaBank"));
//        assertEquals(bank, new Bank("B0001", "CaixaBank"));
//        assertEquals(bank, new Bank("", "CaixaBank"));
//        assertEquals(bank, new Bank("0", "CaixaBank"));
    }


}
