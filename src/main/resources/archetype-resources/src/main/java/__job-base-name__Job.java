#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class ${job-base-name}Job extends Configured implements Tool {

    public static class ${job-base-name}Mapper
        extends Mapper<LongWritable, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class ${job-base-name}Reducer
    extends Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values,
                Context context) throws IOException, InterruptedException {

            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    @Parameter(names = { "-i", "--input" }, description = "Input Directory", required = true)
    private String inputDir;

    @Parameter(names = { "-o", "--output" }, description = "Output Directory", required = true)
    private String outputDir;

    @Parameter(names = "--help", help = true)
    private boolean help;

    public int run(String [] args) throws Exception {
        if (!parseArgs(args)) return -1;
        
        Job job = new Job(getConf());
        job.setJarByClass(${job-base-name}Job.class);
        job.setJobName("${artifactId}");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(${job-base-name}Mapper.class);
        job.setCombinerClass(${job-base-name}Reducer.class);
        job.setReducerClass(${job-base-name}Reducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(inputDir));
        FileOutputFormat.setOutputPath(job, new Path(outputDir));

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public boolean parseArgs(String[] args) {
        JCommander jc = new JCommander(this);
        try {
            jc.parse(args);
            if (help) {
                usage(null, jc);
                return false;
            }
        }
        catch (ParameterException ex) {
            usage(ex.getMessage(), jc);
            return false;
        }
        return true;
    }

    public static void usage(String message, JCommander jc) {
        if (message != null) 
            System.err.println(message);
        ToolRunner.printGenericCommandUsage(System.err);
        StringBuilder out = new StringBuilder();
        jc.usage(out);
        System.err.println(out);
    }

    public static void main(String[] args) throws Exception {
        int ret = ToolRunner.run(new ${job-base-name}Job(), args);
        System.exit(ret);
    }
}
