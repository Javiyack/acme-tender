
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrative;
import domain.Commercial;
import domain.Curriculum;
import domain.File;
import domain.SubSection;
import domain.Tender;
import domain.TenderResult;
import repositories.FileRepository;

@Service
@Transactional
public class FileService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private FileRepository			fileRepository;

	//Services
	@Autowired
	private CommercialService		commercialService;
	@Autowired
	private AdministrativeService	administrativeService;
	@Autowired
	private TenderService			tenderService;
	@Autowired
	private CurriculumService		curriculumService;
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private SubSectionService		subSectionService;


	// Constructor ----------------------------------------------------------
	public FileService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public File createForSubSection(final int subSectionId) {
		Administrative administrative = null;
		Commercial commercial = null;

		final SubSection subSection = this.subSectionService.findOne(subSectionId);
		Assert.notNull(subSection);

		if (subSection.getAdministrative() != null) {
			administrative = this.administrativeService.findByPrincipal();
			Assert.notNull(administrative);
			Assert.isTrue(subSection.getAdministrative().getId() == administrative.getId());
		}

		if (subSection.getCommercial() != null) {
			commercial = this.commercialService.findByPrincipal();
			;
			Assert.notNull(commercial);
			Assert.isTrue(commercial.getId() == subSection.getCommercial().getId());
		}

		final File file = new File();
		file.setSubSection(subSection);
		
		return file;
	}

	public File createForCurriculum(final int curriculumId) {
		Administrative administrative = null;
		Commercial commercial = null;

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);
		Assert.notNull(curriculum);

		if (curriculum.getSubSection().getAdministrative() != null) {
			administrative = this.administrativeService.findByPrincipal();
			Assert.notNull(administrative);
			Assert.isTrue(curriculum.getSubSection().getAdministrative().getId() == administrative.getId());
		}

		if (curriculum.getSubSection().getCommercial() != null) {
			commercial = this.commercialService.findByPrincipal();
			;
			Assert.notNull(commercial);
			Assert.isTrue(curriculum.getSubSection().getCommercial().getId() == commercial.getId());
		}

		final File file = new File();
		file.setCurriculum(curriculum);

		return file;
	}

	public File createForTender(final int tenderId) {
		final Tender tender = this.tenderService.findOneToEdit(tenderId);

		final File file = new File();
		file.setTender(tender);
		return file;
	}

	public File createForTenderResult(final int tenderResultId) {
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);

		final TenderResult tenderResult = this.tenderResultService.findOne(tenderResultId);
		Assert.notNull(tenderResult);
		Assert.isTrue(tenderResult.getTender().getAdministrative().getId() == administrative.getId());

		final File file = new File();
		file.setTenderResult(tenderResult);
		return file;
	}

	public File findOne(final int fileId) {
		File result;
		//final Administrator admin = this.administratorService.findByPrincipal();
		//Assert.notNull(admin);

		result = this.fileRepository.findOne(fileId);
		Assert.notNull(result);

		return result;
	}

	public Collection<File> findAllByTender(final int tenderId) {

		Collection<File> result;

		//final Administrator admin = this.administratorService.findByPrincipal();
		//Assert.notNull(admin);

		result = this.fileRepository.findByTender(tenderId);
		Assert.notNull(result);

		return result;
	}

	public Collection<File> findAllByCurriculum(final int curriculumId) {

		Collection<File> result;

		//final Administrator admin = this.administratorService.findByPrincipal();
		//Assert.notNull(admin);

		result = this.fileRepository.findByCurriculum(curriculumId);
		Assert.notNull(result);

		return result;
	}

	public Collection<File> findAllByTenderResult(final int tenderResultId) {

		Collection<File> result;

		//final Administrator admin = this.administratorService.findByPrincipal();
		//Assert.notNull(admin);

		result = this.fileRepository.findByTenderResult(tenderResultId);
		Assert.notNull(result);

		return result;
	}

	public Collection<File> findAllBySubSection(final int subSectionId) {

		Collection<File> result;

		//final Administrator admin = this.administratorService.findByPrincipal();
		//Assert.notNull(admin);

		result = this.fileRepository.findBySubsection(subSectionId);
		Assert.notNull(result);

		return result;
	}

	public boolean canEditFile(final File file) {

		Commercial commercial = null;
		Administrative administrative = null;
		Assert.notNull(file);

		if (file.getTender() != null) {
			administrative = this.administrativeService.findByPrincipal();
			Assert.notNull(administrative);
			Assert.isTrue(file.getTender().getAdministrative().getId() == administrative.getId());
			return true;
		}

		if (file.getTenderResult() != null) {
			administrative = this.administrativeService.findByPrincipal();
			Assert.notNull(administrative);
			Assert.isTrue(file.getTenderResult().getTender().getAdministrative().getId() == administrative.getId());
			return true;
		}

		if (file.getCurriculum() != null) {
			if (file.getCurriculum().getSubSection().getCommercial() != null) {
				commercial = this.commercialService.findByPrincipal();
				Assert.isTrue(commercial.getId() == file.getCurriculum().getSubSection().getCommercial().getId());
				return true;
			}
		}

		if (file.getSubSection() != null) {
			if (file.getSubSection().getCommercial() != null) {
				commercial = this.commercialService.findByPrincipal();
				Assert.isTrue(commercial.getId() == file.getSubSection().getCommercial().getId());
				return true;
			}

			if (file.getSubSection().getAdministrative() != null) {
				administrative = this.administrativeService.findByPrincipal();
				Assert.isTrue(administrative.getId() == file.getSubSection().getAdministrative().getId());
				return true;
			}
		}

		return false;
	}

	public File save(final File file) {

		if (this.canEditFile(file)) {
			file.setUploadDate(new Date());
			final File saved = this.fileRepository.save(file);

			return saved;
		}

		return null;

	}

	public void delete(final File file) {
		if (this.canEditFile(file))
			this.fileRepository.delete(file);
	}

	public void deleteInBatch(final Collection<File> files) {
		Assert.notNull(files);
		this.fileRepository.deleteInBatch(files);

	}

	public void flush() {
		this.fileRepository.flush();

	}

}
