package com.salim.systempub.controllers;

import org.springframework.ui.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salim.systempub.config.AuthConfig;
import com.salim.systempub.models.chamberlain.Chamberlain;
import com.salim.systempub.models.chamberlain.PUBBalance;
import com.salim.systempub.repository.chamberlain.ChamberlainRepository;
import com.salim.systempub.repository.chamberlain.PUBBalanceRepository;

@Controller
@RequestMapping("/bendahara")
public class BendaharaController extends AuthConfig {

    @Autowired
    private ChamberlainRepository chamberlainRepository;
    @Autowired
    private PUBBalanceRepository pubBalanceRepository;

    @GetMapping("/")
    public String home(Model model) {
        Long lastIncomeBalance=0L;
        Long lastExpenseBalance=0L;
        Long lastTotalBalance=0L;
        Long totalBalance=0L;
        for (Chamberlain chamberlain : chamberlainRepository.findAll()) {
            lastExpenseBalance=chamberlain.getExpense();
            lastIncomeBalance=chamberlain.getIncome();
            lastTotalBalance=chamberlain.getBalance();
        }
        for (PUBBalance pubBalance : pubBalanceRepository.findAll()) {
            totalBalance=pubBalance.getTotalBalance();
        }
        model.addAttribute("lastIncomeBalance",lastIncomeBalance);
        model.addAttribute("lastExpenseBalance",lastExpenseBalance);
        model.addAttribute("lastTotalBalance",lastTotalBalance);
        model.addAttribute("totalBalance",totalBalance);
        model.addAttribute("balance",pubBalanceRepository.findAll());
        model.addAttribute("data",chamberlainRepository.findAll());
        model.addAttribute("add",new Chamberlain());
        return "bendahara/index";
    }
    @GetMapping("/list/")
    public String listPendapatanPengeluaran(Model model){
        model.addAttribute("balance", pubBalanceRepository.findAll());
        model.addAttribute("data",chamberlainRepository.findAll());
        return "bendahara/list";
    }
    @GetMapping("/add/")
    public String addData(Model model){
        Chamberlain bendahara = new Chamberlain();
        model.addAttribute("add",bendahara);
        return "bendahara/add";
    }
    @GetMapping("/delete/v1/{id}")
    public String delete(@PathVariable(value = "id")Long id,Model model){
        chamberlainRepository.deleteById(id);
        return "redirect:/bendahara/list/";
    }
    @GetMapping("/delete/v2/{id}")
    public String delete2String(@PathVariable(value = "id")Long id,Model model){
        pubBalanceRepository.deleteById(id);
        return "redirect:/bendahara/list/";
    }
    @PostMapping("/save")
    public String save(Chamberlain chamberlain){
        Long lastId=0L;
        PUBBalance balance=new PUBBalance();
        PUBBalance balancePrimary;
        List<PUBBalance> PUB = pubBalanceRepository.findAll();
        for (PUBBalance pubBalance : PUB) {
            lastId=pubBalance.getId();
        }
        if(!pubBalanceRepository.findAll().isEmpty()){balancePrimary=pubBalanceRepository.getReferenceById(lastId);}
        else{balancePrimary=balance;}
        balance.setDate(chamberlain.getDate());
        balance.setSavingBalance(chamberlain.getIncome());
        balance.setUsedBalance(chamberlain.getExpense());
        chamberlain.setBalance(balancePrimary.getTotalBalance());
        if(balance.getSavingBalance()==0)balance.setTotalBalance(balance.getSavingBalance()-balance.getUsedBalance());
        balance.setTotalBalance((balancePrimary.getTotalBalance()+balance.getSavingBalance())-balance.getUsedBalance());
        chamberlainRepository.save(chamberlain);
        pubBalanceRepository.save(balance);
        return "redirect:/bendahara/";
    }
}
