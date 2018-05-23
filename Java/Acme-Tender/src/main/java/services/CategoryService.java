
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Administrator;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	//Repository
	@Autowired
	private CategoryRepository		categoryRepository;

	@Autowired
	private AdministratorService	administratorService;


	//CRUDS

	//Create
	public Category create() {
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		Category result;
		result = new Category();
		return result;
	}
	//Save

	public Category save(final Category category) {
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		Assert.notNull(category);
		final Category saved = this.categoryRepository.save(category);
		return saved;
	}
	//Delete
	public void delete(final Category category) {
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		Assert.isNull(this.categoryRepository.haveTender(category.getId()), "category.delete.error");
		Assert.notNull(category);
		Assert.isTrue(this.getChildCategories(category.getId()).isEmpty());
		Collection<Category> childCategories;
		childCategories = this.categoryRepository.getChildCategories(category.getId());
		if (!childCategories.isEmpty())
			this.categoryRepository.delete(childCategories);
		this.categoryRepository.delete(category);
	}
	//findOne
	public Category findOne(final int categoryId) {
		Category result;
		result = this.categoryRepository.findOne(categoryId);
		Assert.notNull(result);
		return result;
	}

	//findAll
	public Collection<Category> findAll() {

		Collection<Category> result;

		result = this.categoryRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	//Other
	public Collection<Category> getChildCategories(final int parentCategoryId) {

		return this.categoryRepository.getChildCategories(parentCategoryId);
	}

	public Collection<Category> getFirstLevelCategories() {

		return this.categoryRepository.getFirstLevelCategories();

	}

}
