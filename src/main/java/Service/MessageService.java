package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }


    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
        accountDAO =  new AccountDAO();
    }


    public MessageService(AccountDAO accountDAO) {
        messageDAO = new MessageDAO();
        this.accountDAO = accountDAO;
    }


    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }


    /**
     * 
     * @param message
     * @return Added Message
     */
    public Message addMessage(Message message) {
        if (message.getMessage_text().length() > 255) {
            return null;
        }
        if (message.getMessage_text() == "") {
            return null;
        }
        if (accountDAO.getAccountById(message.getPosted_by()) == null) {
            return null;
        }

        return messageDAO.insertMessage(message);
    }


    /**
     * 
     * @return List of Messages
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }


    /**
     * 
     * @param id
     * @return Retrieved Message
     */
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }


    /**
     * 
     * @param id
     * @return Deleted Message
     */
    public Message deleteMessageById(int id) {
        Message message = messageDAO.getMessageById(id);
        messageDAO.deleteMessageByID(id);
        return message;
    }


    /**
     * 
     * @param id
     * @param message
     * @return Newly Updated Message
     */
    public Message updateMessage(int id, Message message) {
        String message_text = message.getMessage_text();

        if (message_text == "") {
            return null;
        }
        if (message_text.length() > 255) {
            return null;
        }
        if (messageDAO.getMessageById(id) == null) {
            return null;
        }

        messageDAO.updateMessage(id, message);
        return messageDAO.getMessageById(id);
    }


    /**
     * 
     * @param id
     * @return List of Messages with matching id
     */
    public List<Message> getAllMessagesByAccount(int id) {
        return messageDAO.getAllMessageByPostedBy(id);
    }

}
