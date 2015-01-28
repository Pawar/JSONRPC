package com.demo.client;

import java.util.ArrayList;
import java.util.Iterator;

import com.terzetto.clientrpc.*;

public class Client {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		RPCClient rpcclient = new RPCClient("localhost", "3887");
		try {
			ArrayList al =  (ArrayList) rpcclient.call("demo1.add", 10,10);
			Iterator it = al.iterator();
			while(it.hasNext()){
				System.out.print(it.next()+ " ");
			}	
		
			int sub =  (int) rpcclient.call("demo1.sub", 10,10);
			System.out.println("\nSub1: " + sub);

			int increament =  (int) rpcclient.call("demo1.increase", 10);
			System.out.println("Incr1: " + increament);
			
			ArrayList al2 =  (ArrayList) rpcclient.call("demo2.add", 100,10);
			Iterator it2 = al2.iterator();
			while(it2.hasNext()){
				System.out.print(it2.next()+" ");
			}	
		
			int sub2 =  (int) rpcclient.call("demo2.sub", 210,10);
			System.out.println("\nSub2: " + sub2);

			int increament2 =  (int) rpcclient.call("demo2.increase", 100);
			System.out.println("Incr2: " + increament2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
