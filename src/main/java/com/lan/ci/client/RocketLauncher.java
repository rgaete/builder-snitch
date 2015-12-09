package com.lan.ci.client;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by rgaete on 05-06-15.
 */
@Getter
@Setter
@RestController("/")
public class RocketLauncher {

	@Value("${jenkins.url}")
	private String url;
	@Value("${jenkins.user}")
	private String user;
	@Value("${jenkins.pass}")
	private String pass;
	@Value("${retaliation.command}")
	private String command;
	private Boolean fired = false;
	
	
	@RequestMapping(value = "/scan", method = RequestMethod.POST)
	public void fire(@RequestBody RequestMessage requestMessage)
			throws IOException, InterruptedException, URISyntaxException {

		try {

			String s = null;
			System.out.println(requestMessage);
			JenkinsServer jenkins = new JenkinsServer(new URI(
					url), user, pass);
			Map<String, Job> jobs = jenkins.getJobs();
			JobWithDetails job = jobs.get(
					requestMessage.getJobName().toLowerCase()).details();
			BuildWithDetails buildWithDetails = job.getLastBuild().details();
			String result = buildWithDetails.getResult().toString();
			System.out.println("LAST JOB: " + result + " - RESULT");

			if ("FAILURE".equals(result) && !fired) {
				System.out.println("Failure - DISPARANDO");
				fired = true; 
				Process p = Runtime
						.getRuntime()
						.exec(command);

				BufferedReader stdInput = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				BufferedReader stdError = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));

				// read the output from the command
				System.out
						.println("Here is the standard output of the command:\n");
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
				}

				// read any errors from the attempted command
				System.out
						.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
			}
			if("SUCCED".equals(result)){
				fired = false;
			}
			// System.exit(0);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}

	}
}