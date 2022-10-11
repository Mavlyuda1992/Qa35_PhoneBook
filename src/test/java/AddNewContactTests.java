import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
    }

    @Test
    public void addNewContactSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("John"+i)
                .lastName("Snow")
                .phone("3434345"+i)
                .email("john"+i+"@mail.com")
                .address("Rehovot")
                .description("The best friend").build();

        app.getContact().openContactForm();
        app.getContact().fillContactAllForm(contact);
        app.getContact().saveContactWithTab();
        Assert.assertTrue(app.getContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Nadin"+i)
                .lastName("Snow")
                .phone("3434345"+i)
                .email("nadin"+i+"@mail.com")
                .address("Haifa")
                .build();

        app.getContact().openContactForm();
        app.getContact().fillContactRequiredForm(contact);
        app.getContact().saveContact();
        Assert.assertTrue(app.getContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addNewContactWrongName(){

        Contact contact = Contact.builder()
                .lastName("Snow")
                .phone("34343452143568")
                .email("zoa@mail.com")
                .address("Haifa")
                .build();

        app.getContact().openContactForm();
        app.getContact().fillContactRequiredForm(contact);
        app.getContact().saveContact();
        Assert.assertTrue(app.getContact().isAddPageStillDisplayed());

    }



}