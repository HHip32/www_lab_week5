package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Candidate;
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

    public String showListCandidate(Model model){
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }
    @GetMapping("/list")
    public String showListCandidatePaging(Model model
            , @RequestParam("page") Optional<Integer> page
            , @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        /*Page<Candidate> candidatePage= candidateServices.findPaginated(
                PageRequest.of(currentPage - 1, pageSize)
        );*/
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
    @PostMapping("/candidates/add")
    public String addCandidate(Candidate candidate, BindingResult result, Model model) {
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/add-candidate")
    public ModelAndView add(Model mode) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("candidate", new Candidate());
        modelAndView.setViewName("candidates/add-candidate");
        return modelAndView;
    }
}
