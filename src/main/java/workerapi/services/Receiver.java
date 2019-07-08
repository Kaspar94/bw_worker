package workerapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import workerapi.models.Vote;
import workerapi.repository.VoteRepository;

@Component
public class Receiver {

	@Autowired
	private VoteRepository vr;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        Vote v = new Vote(message);
        vr.saveAndFlush(v);
    }
}