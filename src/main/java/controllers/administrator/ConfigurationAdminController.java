
package controllers.administrator;


// @Controller
// @RequestMapping("/configuration/administrator")
// public class ConfigurationAdminController extends AbstractController {
//
// @Autowired
// ConfigurationService configurationService;
//
// @Autowired
// AdministratorService administratorService;
//
//
// // Constructors -----------------------------------------------------------
//
// public ConfigurationAdminController() {
// super();
// }
//
// // ---------------------------------------------------------------
//
// @RequestMapping(value = "/edit", method = RequestMethod.GET)
// public ModelAndView edit() {
//
// ModelAndView result;
//
// // final Collection<domain.Configuration> configurations = this.configurationService.findAll();
//
// result = new ModelAndView("configuration/administrator/edit");
// // result.addObject("configuration", configurations.toArray()[0]);
//
// return result;
// }
//
// @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
// public ModelAndView save(@Valid final domain.Configuration configuration, final BindingResult binding) {
//
// ModelAndView result = null;
//
// if (binding.hasErrors()) {
// result = new ModelAndView("configuration/administrator/edit");
// result.addObject("configuration", configuration);
//
// } else
// try {
// // this.configurationService.save(configuration);
// // result = this.createEditModelAndView("configuration.commit.ok", "/");
// } catch (final Throwable oops) {
// // result = this.createEditModelAndView("configuration.commit.ko", "/");
// }
// return result;
// }
//
// protected ModelAndView createEditModelAndView(final MyMessage m) {
// ModelAndView result;
//
// result = this.createEditModelAndView(m, null);
//
// return result;
// }
//
// protected ModelAndView createEditModelAndView(final MyMessage m, final String messageCode) {
// ModelAndView result;
// result = new ModelAndView("myMessage/create");
// result.addObject("modelMessage", m);
// result.addObject("message", messageCode);
// result.addObject("requestUri", "myMessage/administrator/edit.do");
//
// return result;
// }
//}
