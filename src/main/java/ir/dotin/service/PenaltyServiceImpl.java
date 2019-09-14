package ir.dotin.service;

import ir.dotin.model.Account;
import ir.dotin.model.Document;
import ir.dotin.model.Penalty;
import ir.dotin.model.User;
import ir.dotin.repository.AccountRepository;
import ir.dotin.repository.DocumentRepository;
import ir.dotin.repository.PenaltyRepository;
import ir.dotin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void punishment(Long userId, Long penaltyId) {
        User user = userRepository.getOne(userId);
        Penalty penalty = penaltyRepository.getOne(penaltyId);
        createDocument(user.getAccount(),penalty.getAmount(),true);
    }

    @Override
    public void addPenalty(Penalty penalty) {
        penaltyRepository.save(penalty);
    }

    @Override
    public void changePenaltyAmount(Long penaltyId, Double newPenaltyAmount) {
        Penalty penalty = penaltyRepository.getOne(penaltyId);
        penalty.setAmount(newPenaltyAmount);
        penaltyRepository.save(penalty);
    }

    @Override
    public List<Penalty> getAllPenalties() {
        return penaltyRepository.findAll();
    }

    @Override
    public void payForPenalty(Long userId, Double amount) {
        User user = userRepository.getOne(userId);
        createDocument(user.getAccount(),amount,false);
    }

    private void createDocument(Account account, Double amount,boolean isDebtor) {
        Document document = new Document();
        document.setAmount(amount);
        document.setDate(LocalDate.now());
        document.setIsDebtor(isDebtor);
        Double newAmount = isDebtor ? account.getAmount() - amount : account.getAmount() + amount;
        account.setAmount(newAmount);
        account.getDocuments().add(document);
        accountRepository.save(account);
    }

}
