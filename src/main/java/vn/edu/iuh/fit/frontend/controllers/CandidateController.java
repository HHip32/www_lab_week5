package vn.edu.iuh.fit.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.CandidateService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/candidates")
    public String showListCandidate(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/list")
    public String showListCandidatePaging(Model model
            , @RequestParam("page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
//        Page<Candidate> candidatePage= candidateService.findPaginated(
//                PageRequest.of(currentPage - 1, pageSize)
//        );
        Page<Candidate> candidatePage = candidateService.findAll(currentPage - 1,
                pageSize, "id", "asc");

        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

    @GetMapping("/show-add-form")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        candidate.setAddress(new Address());
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("address", candidate.getAddress());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("candidates/add");
        return modelAndView;
    }

    @PostMapping("/candidates/add")
    public String addCandidate(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address
    ) {
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:candidates/candidates-paging";
    }
    @GetMapping("/show-edit-form/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Candidate> opt = candidateRepository.findById(id);
        if(opt.isPresent()) {
            Candidate candidate = opt.get();
            modelAndView.addObject("candidate", candidate);
            modelAndView.addObject("address", candidate.getAddress());
            modelAndView.addObject("countries", CountryCode.values());
            modelAndView.setViewName("candidates/update");
        }
        return modelAndView;
    }
    @PostMapping("/candidates/update/{id}")
    public String update(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address) {
        addressRepository.save(address);
//        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/candidates/delete/{id}")
    public String delete(@PathVariable("id") long id){
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if(optionalCandidate.isPresent()){
            candidateRepository.delete(optionalCandidate.get());
        }
        return "redirect:/candidates";
    }

}