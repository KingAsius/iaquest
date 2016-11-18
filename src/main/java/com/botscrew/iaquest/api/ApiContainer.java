package com.botscrew.iaquest.api;

import com.botscrew.iaquest.model.Command;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.Postback;

public interface ApiContainer {

	public void sendMessage(Message message);

	public void receiveCommand(Command command);

	public void receivePostback(Postback postback);

}
