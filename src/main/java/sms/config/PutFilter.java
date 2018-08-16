package sms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * 使http put方法可以接收表单数据
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}
