//package app;
//
//import app.fuzzy_sets.ClassicSet;
//import app.fuzzy_sets.FuzzySet;
//import app.fuzzy_sets.FuzzySetOperations;
//import app.fuzzy_sets.characterictic_functions.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class App2 {
//    public static void main(String[] args) {
//
//        List<Double> xValues = new ArrayList<>();
//        xValues.addAll(Arrays.asList(Double.valueOf(0), Double.valueOf(1),
//                Double.valueOf(2), Double.valueOf(3), Double.valueOf(4),
//                Double.valueOf(5), Double.valueOf(6), Double.valueOf(7),
//                Double.valueOf(8), Double.valueOf(9), Double.valueOf(10)));
//        ClassicSet X = new ClassicSet(xValues, new ClassicFunction());
//
//        CharacteristicFunction aFunction = new FallingFunction(2, 5);
//
//        FuzzySet A = new FuzzySet(aFunction, X);
//
//        System.out.println(A.toString());
//
//        FuzzySet complement = A.getComplement();
//        System.out.println(complement.toString());
//
//        ClassicSet support = A.getSupport();
//        //System.out.println(support.toString());
//
//        ClassicSet a50 = A.getStrongAlphaCut(0.5);
//        //System.out.println(a50.toString());
//
//        FuzzySet intersetion = FuzzySetOperations.getIntersection(A, complement);
//        System.out.println(intersetion.toString());
//
//        FuzzySet union = FuzzySetOperations.getUnion(A, complement);
//        System.out.println(union.toString());
//
//    }
//}