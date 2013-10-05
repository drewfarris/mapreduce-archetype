#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.IOException;
import java.util.Vector;
import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import ${package}.${job-base-name}Job.${job-base-name}Reducer;

public class ${job-base-name}ReducerTest extends TestCase {
	private Reducer<Text, IntWritable, Text, IntWritable> reducer;
	private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;
	
	@Before
	public void setUp() {
		reducer = new ${job-base-name}Reducer();
		driver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
	}
	
	@Test
	public void testCountOccurancesOfWordFromListOfOnes() throws IOException {
		int expectedWordCount = 10;
		
		Vector<IntWritable> tenOnes = new Vector<IntWritable>();
		
		for (int i = 0; i < expectedWordCount; i++) {
			tenOnes.add( new IntWritable(1) );
		}
		
		driver.withInput(new Text("theword"), tenOnes)
			  .withOutput(new Text("theword"), new IntWritable(expectedWordCount) )
			  .runTest();
	}
}
