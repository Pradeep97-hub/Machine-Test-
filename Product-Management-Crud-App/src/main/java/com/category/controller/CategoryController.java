package com.category.controller;

import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.category.entity.Category;
import com.category.repository.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/")
	public String home(Model m) {

		/*
		 * List<Products> list = productRepo.findAll(); m.addAttribute("all_products",
		 * list);
		 */

		return findPaginateAndSorting(0,"id","asc", m);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginateAndSorting(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model m) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo, 3,sort);

		Page<Category> page = categoryRepo.findAll(pageable);

		List<Category> list = page.getContent();

		m.addAttribute("pageNo", pageNo);
		m.addAttribute("totalElements", page.getTotalElements()); 
		m.addAttribute("totalPage", page.getTotalPages());
		m.addAttribute("all_products", list);
		
		m.addAttribute("sortField",sortField);
		m.addAttribute("sortDir",sortDir);
		m.addAttribute("revSortDir",sortDir.equals("asc") ? "desc" : "asc");
		

		return "index";
	}

	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	@GetMapping("/edit_form/{id}")
	public String editForm(@PathVariable(value = "id") long id, Model m) {

		Optional<Category> category = categoryRepo.findById(id);

 		Category pro = category.get();
		m.addAttribute("category", pro);

		return "edit";
	}

	@PostMapping("/save_category")
	public String saveProducts(@ModelAttribute Category category, HttpSession session) {

		categoryRepo.save(category);
		session.setAttribute("msg", "Category Added Sucessfully..");

		return "redirect:/load_form";
	}

	@PostMapping("/update_category")
	public String updateCategory(@ModelAttribute Category category, HttpSession session) {

		categoryRepo.save(category);
		session.setAttribute("msg", "Category Update Sucessfully..");

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable(value = "id") long id, HttpSession session) {
		categoryRepo.deleteById(id);
		session.setAttribute("msg", "Category Delete Sucessfully..");

		return "redirect:/";

	}

}
