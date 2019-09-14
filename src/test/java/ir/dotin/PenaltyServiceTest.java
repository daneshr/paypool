package ir.dotin;

import ir.dotin.model.Account;
import ir.dotin.model.Document;
import ir.dotin.model.Penalty;
import ir.dotin.model.User;
import ir.dotin.repository.AccountRepository;
import ir.dotin.repository.PenaltyRepository;
import ir.dotin.repository.UserRepository;
import ir.dotin.service.PenaltyServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PenaltyServiceTest {
    @InjectMocks
    private PenaltyServiceImpl penaltyService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PenaltyRepository penaltyRepositoryMock;

    @Mock
    private AccountRepository accountRepositoryMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void punishmentTest(){
        Long penaltyId = 0l;
        Long userId = 0l;
        Account account = getMochAccount();
        User user = getMockUser(account);
        when(userRepositoryMock.getOne(userId)).thenReturn(user);
        Penalty penalty = getMockPenalty(new Double(3322));
        when(penaltyRepositoryMock.getOne(penaltyId)).thenReturn(penalty);
        when(accountRepositoryMock.save(account)).thenReturn(account);
        penaltyService.punishment(userId,penaltyId);
        Assert.assertEquals(user.getAccount().getAmount(),new Double(-27921));
        Assert.assertEquals(user.getAccount().getDocuments().get(1).getAmount(),new Double(3322));
        Assert.assertEquals(user.getAccount().getDocuments().get(1).getIsDebtor(),true);
    }

    @Test
    public void changePenaltyAmountTest(){
        Long penaltyId = 0l;
        Penalty penalty = getMockPenalty(new Double(3322));
        when(penaltyRepositoryMock.getOne(penaltyId)).thenReturn(penalty);
        when(penaltyRepositoryMock.save(penalty)).thenReturn(penalty);
        penaltyService.changePenaltyAmount(penaltyId,new Double(131313));
        Assert.assertEquals(penalty.getAmount(),new Double(131313));
    }

    @Test
    public void payForPenaltyTest(){
        Long userId = 0l;
        Account account = getMochAccount();
        User user = getMockUser(account);
        when(userRepositoryMock.getOne(userId)).thenReturn(user);
        when(accountRepositoryMock.save(account)).thenReturn(account);
        penaltyService.payForPenalty(userId, new Double(333));
        Assert.assertEquals(account.getAmount(),new Double(-24266));
        Assert.assertEquals(user.getAccount().getDocuments().get(1).getAmount(),new Double(333));
        Assert.assertEquals(user.getAccount().getDocuments().get(1).getIsDebtor(),false);
    }



    private Penalty getMockPenalty(Double amount){
        Penalty penalty = new Penalty();
        penalty.setDefinition("Test penalty");
        penalty.setAmount(amount);
        return penalty;
    }

    private User getMockUser(Account account){
        User user = new User();
        user.setIsActive(true);
        user.setAccount(account);
        return user;
    }

    private Account getMochAccount(){
        Account account = new Account();
        account.setAmount(new Double(-24599));
        Document document = new Document();
        document.setAmount(new Double(24599));
        document.setIsDebtor(true);
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        account.setDocuments(documents);
        return account;
    }


}
