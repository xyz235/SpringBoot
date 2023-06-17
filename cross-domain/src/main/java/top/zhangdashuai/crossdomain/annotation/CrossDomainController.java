package top.zhangdashuai.crossdomain.annotation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangdashaui
 */
@RestController
@CrossOrigin("*")
public class CrossDomainController {
}
