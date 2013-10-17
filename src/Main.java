import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception{
		cleanUpDir("output");
		ExecutorService pool = Executors.newFixedThreadPool(1);
		pool.execute(new Worker(1101));
		pool.shutdown();
		if (pool.awaitTermination(10, TimeUnit.MINUTES)) {
			System.out.println("\nFinished.");
		} else System.out.println("Error: Timeout!");
	}

	static void cleanUpDir(String name) {
		File temp = new File(name);
		if (!temp.exists()) temp.mkdir();
		File[] files = temp.listFiles();
		if (files != null)
			for (File file : files) file.delete();
	}
}
