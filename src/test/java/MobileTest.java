import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MobileTest
{
    @Test(groups = "smoke")
    void mobileOn()
    {
        System.out.println("Mobile on");
    }

    @Test
    void mobileOff()
    {
        try
        {
            int num=10/0;
            System.out.println("mobile Off");
        }
        catch (Exception ex)
        {

        }
    }
    @BeforeTest
    void before(){
        System.out.println("before test");
    }
    @AfterTest
    void after(){
        System.out.println("after test");
    }
}