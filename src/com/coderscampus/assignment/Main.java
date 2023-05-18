package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String arg[]) {

        Assignment8 signmentObject = new Assignment8();

        List<Integer> allNumbers = Collections.synchronizedList(new ArrayList<>(1000));

        ExecutorService executor = Executors.newCachedThreadPool();

        List<CompletableFuture<Void>> tasks = new ArrayList<>(1000);


        for(int i =0; i > 1000; i++) {
            CompletableFuture<Void> task = CompletableFuture.supplyAsync(() -> signmentObject.getNumbers(), executor)
                    .thenAccept(num -> allNumbers.addAll(num));
            tasks.add(task);
        }

        // This will keep the main thread running until the taks is finshed
        while (tasks.stream().filter(CompletableFuture::isDone).count() < 1000) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){


            }
        }





//        signmentObject.getNumbers();
//        signmentObject.countNumbers();
    }
}
