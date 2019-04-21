package test;

import org.junit.Test;
import static org.junit.Assert.*;

import animals.*;
import product.*;

public class AnimalTest{
    @Test
    public void testChicken() throws Exception{
        Chicken chick = Chicken(2,5,true);
        assertEquals(chick.getLapar(),true);
        assertEquals(chick.getThreshold(),8);
        assertEquals(chick.getX(),2);
        assertEquals(chick.getY(),5);
        chick.minThreshold();
        assertEquals(chick.getThreshold(),7);
        FarmProduct prod = chick.produceEgg();
        assertEquals(prod.getProductName(),"ChickenEgg");
        FarmProduct prodx = chick.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(chick.showSimbol(),'c');
        chick.revLapar();
        assertEquals(chick.getLapar(),false);
    }

    @Test
    public void testCow() throws Exception{
        Cow cow = Cow(1,4,true);
        assertEquals(cow.getLapar(),true);
        assertEquals(cow.getThreshold(),10);
        assertEquals(cow.getX(),1);
        assertEquals(cow.getY(),4);
        cow.minThreshold();
        assertEquals(cow.getThreshold(),9);
        FarmProduct prod = cow.produceMilk();
        assertEquals(prod.getProductName(),"CowMilk");
        FarmProduct prodx = cow.produceEgg();
        assertEquals(prodx.getProductName(),"");
        assertEquals(cow.showSimbol(),'s');
        cow.revLapar();
        assertEquals(cow.getLapar(),false);
    }

    @Test
    public void testDuck() throws Exception{
        Duck duck = Duck(10,8,false);
        assertEquals(duck.getLapar(),false);
        assertEquals(duck.getThreshold(),8);
        assertEquals(duck.getX(),10);
        assertEquals(duck.getY(),8);
        duck.minThreshold();
        assertEquals(duck.getThreshold(),7);
        FarmProduct prod = duck.produceEgg();
        assertEquals(prod.getProductName(),"DuckEgg");
        FarmProduct prodx = duck.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(duck.showSimbol(),'D');
        duck.revLapar();
        assertEquals(duck.getLapar(),true);
    }

    @Test
    public void testGoat() throws Exception{
        Goat goat = Goat(3,7,false);
        assertEquals(goat.getLapar(),false);
        assertEquals(goat.getThreshold(),10);
        assertEquals(goat.getX(),3);
        assertEquals(goat.getY(),7);
        goat.minThreshold();
        assertEquals(goat.getThreshold(),9);
        FarmProduct prod = goat.produceMilk();
        assertEquals(prod.getProductName(),"GoatMilk");
        FarmProduct prodx = goat.produceEgg();
        assertEquals(prodx.getProductName(),"");
        assertEquals(goat.showSimbol(),'G');
        goat.revLapar();
        assertEquals(goat.getLapar(),true);
    }

    @Test
    public void testHorse() throws Exception{
        Horse horse = Horse(8,5,true);
        assertEquals(horse.getLapar(),true);
        assertEquals(horse.getThreshold(),12);
        assertEquals(horse.getX(),8);
        assertEquals(horse.getY(),5);
        horse.minThreshold();
        assertEquals(horse.getThreshold(),11);
        FarmProduct prod = horse.produceMeat();
        assertEquals(prod.getProductName(),"HorseMeat");
        FarmProduct prodx = horse.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(horse.showSimbol(),'h');
        horse.revLapar();
        assertEquals(horse.getLapar(),false);
    }

    @Test
    public void testPig() throws Exception{
        Pig pig = Pig(4,4,false);
        assertEquals(pig.getLapar(),false);
        assertEquals(pig.getThreshold(),12);
        assertEquals(pig.getX(),4);
        assertEquals(pig.getY(),4);
        pig.minThreshold();
        assertEquals(pig.getThreshold(),11);
        FarmProduct prod = pig.produceMeat();
        assertEquals(prod.getProductName(),"PigMeat");
        FarmProduct prodx = pig.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(pig.showSimbol(),'B');
        pig.revLapar();
        assertEquals(pig.getLapar(),true);
    }
}