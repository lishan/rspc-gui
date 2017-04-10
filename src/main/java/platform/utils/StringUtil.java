package platform.utils;

/**
 * <p> StringUtil.java </p>
 * <p>
 * </p>
 * @author $Author: gggao $
 * @version $Revision: 1.18 $
 */
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * StringUtil.java
 * </p>
 * <p>
 * 处理字符的工具类
 * </p>
 * 
 * @author gggao
 * 
 */
public class StringUtil {
	/**
	 * <p>
	 * 根据正则表达式，获取首次匹配的字符串
	 * </p>
	 * 
	 * @author gggao
	 * @date 2010-6-26
	 * @param source
	 * @param rule
	 * @return
	 */
	public static String regexFindFirstValue(String source, String rule) {
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

	/**
	 * 从指定的字条串中，通过正则表达式，查找匹配子字符串
	 * <H1>例如</H2>
	 * <LI>source：sum(sum(#{a},#{b},#{c},#{d}))#abcd{abcd}
	 * <LI>patternString：(?<=\u0023\\u007B)([^\\u007D]+?)(?=\\u007D) => (?<=#{)([^}])(?=})
	 * <LI>结果：[a,b,c,d]
	 * 
	 * @param source
	 *            源数据串
	 * @param pattern
	 *            正则规则
	 * @return
	 */
	public static List<String> regexFindCharter(String source, String pattern) {
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(source) || StringUtils.isBlank(pattern)){
			// 如果没有提供源数据及匹配规则，则返回空集合
			return result;
		}
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(source);
		while (matcher.find()) {
			result.add(matcher.group());
		}
		return result;
	}
	/**
	 * 从指定的字条串中，通过正则表达式，查找匹配子字符串
	 * <H1>例如</H2>
	 * <LI>source：sum(sum(#{a},#{b},#{c},#{d}))#abcd{abcd}
	 * <LI>patternString：(?<=\u0023\\u007B)([^\\u007D]+?)(?=\\u007D) => (?<=#{)([^}])(?=})
	 * <LI>结果：[a,b,c,d]
	 * 
	 * @param source
	 *            源数据串
	 * @param pattern
	 *            正则规则
	 * @return
	 */
	public static List<String> regexFindCharterItem(String source, String pattern){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(source) || StringUtils.isBlank(pattern)){
			// 如果没有提供源数据及匹配规则，则返回空集合
			return result;
		}
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(source);
		while (matcher.find()) {
			for(int i=1;i<=matcher.groupCount();i++){   
				result.add(matcher.group(i));   
			}  
		}
		return result;
	}

	/**
	 * 基于正则，忽略字符串大小写，并对字符串进行替换。
	 * <LI>例如：replaceIgnoreCase("bCd", "c", "G"),结果:"bGd"
	 * 
	 * @param source
	 *            原字段串
	 * @param oldChar
	 *            替换字符串
	 * @param newChar
	 *            新字符串
	 * @return
	 */
	public static String replaceIgnoreCase(String source, String oldChar,
			String newChar) {
		return source.replaceAll("(?i)" + oldChar, newChar);
	}

	/**
	 * <p>
	 * 字符串分割后，是否包含另一个子串
	 * </p>
	 * 
	 * @author gggao
	 * @date 2010-7-1
	 * @param source
	 *            源字符串
	 * @param separatorChar
	 *            分隔符
	 * @param substring
	 *            查找的子串
	 * @return
	 */
	public static boolean contain(String source, String separatorChar,
			String substring) {
		boolean result = false;
		if (source == null || substring == null) {
			return result;
		}
		// 剔除空白后，把这个串用 分隔符 分割
		String[] sources = StringUtils.split(StringUtils
				.deleteWhitespace(source), separatorChar);
		for (String ss : sources) {
			if (ss.equals(substring)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 忽略大小写,判断目标串是否包含子串
	 * @param source 目标串
	 * @param compare 查找串
	 * @return 是否包含
	 *
	 * @author gggao
	 *
	 * @date Mar 29, 2013 4:48:16 PM 
	 */
	public static boolean contain(String source, String compare){
	    if(StringUtils.isEmpty(source) || StringUtils.isEmpty(compare)){
		return false;
	    }
	    return source.replaceAll("(?i)" + compare, "").length() != source.length() ? true : false;
	}

	/**
	 * 剔除字符串中，首尾的 分隔符
	 * <LI>原串为 ",1,2," 运算后为 "1,2"
	 * <LI>原串为 ",,1,2,," 运算后为 "1,2"
	 * <LI>原串为 ",,1,,2,," 运算后为 "1,,2", 注(只剔除首尾位置的分隔符)
	 * <LI>注：分隔符为"."时，需要进行转义"\\."
	 * 
	 * @author gggao
	 * @date 2010-7-21
	 * @param source
	 *            原字符串
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static String trimStartAndEndSeparatorChar(String source,
			String separatorChar) {
		return source.replaceAll("^" + separatorChar + "+", "").replaceAll(
				separatorChar + "+$", "");
	}

	/**
	 * 剔除多余的 分隔符,包括首尾的 分隔符
	 * <LI>原串为 ",1,2," 运算后为 "1,2"
	 * <LI>原串为 ",,1,2,," 运算后为 "1,2"
	 * <LI>原串为 ",,1,,2,," 运算后为 "1,2"
	 * <LI>原串为 ",,1,,,2,," 运算后为 "1,2"
	 * <LI>原串为 ",,1,,,,2,," 运算后为 "1,2"
	 * <LI>注：分隔符为"."时，需要进行转义"\\."
	 * 
	 * @param source
	 *            原字符串
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static String trimRedundantSeparatorChar(String source,
			String separatorChar) {
		return trimStartAndEndSeparatorChar(source.replaceAll(separatorChar
				+ "(\\s*" + separatorChar + ")+", separatorChar), separatorChar);
	}

	/**
	 * 连接数组内容，拼接后格式为 前缀 + 值 + 后缀 ( + 分隔符 )
	 * <LI>分隔符为"."时，需要转义为"\\."
	 * <LI>数组中某个值为 null 时，拼接时被忽略
	 * 
	 * @author gggao
	 * @date 2010-7-21
	 * @param values
	 *            值
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param separatorChar
	 *            分隔符
	 * @return
	 */
	public static String join(Object[] values, String prefix, String suffix,
			String separatorChar) {
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (Object value : values) {
			if (value != null) {
				// 如果 分隔符 为 null 或 “ ” 空白串时，则拼接到结果中
				if (flag && StringUtils.isNotBlank(separatorChar)) {
					result.append(separatorChar);
				} else {
					flag = true;
				}
				result.append(prefix + value.toString() + suffix);
			}
		}
		return result.toString();
	}

	/**
	 * 判断 content 里面是否包含中文<BR>
	 * <font color=red><B>注:</B>较为传统的是使用 正则，这里从 java 字节存储上面判断</font>
	 * 
	 * @param content
	 *            判断内容
	 * @return
	 */
	public static Boolean isContainChinese(String content) {
		if (StringUtils.isBlank(content)) {
			return Boolean.FALSE;
		}
		return content.getBytes().length == content.length() ? Boolean.FALSE
				: Boolean.TRUE;
	}
	
	/**
	 * 字符或中文 转换为 unicode 编码，用于正则规则或其他不能直接使用字符的场景中
	 * 
	 * @param character
	 *            字符或中文
	 * @return
	 */
	public static String character2Unicode(String character) {
		int len = character.length();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < len; i++) {
			char c = character.charAt(i);
			String tempChar = Integer.toHexString(c);
			result.append("\\u" + StringUtils.leftPad(tempChar, 4, "0"));
		}
		return result.toString();
	}
	
	
	/**
	 * URL 转码, 默认使用 UTF-8 编码
	 * @param content
	 * @return
	 */
	public static String encode(String content){
		return encode(content, "UTF-8");
	}
	
	/**
	 * URL 转码
	 * @param content
	 * @param enc
	 * @return
	 */
	public static String encode(String content, String enc){
		try {
			return URLEncoder.encode(content, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * URL 解码, 默认使用 UTF-8 编码
	 * @param content
	 * @return
	 */
	public static String decode(String content){
		return decode(content, "UTF-8");
	}
	
	/**
	 * URL 解码
	 * @param content
	 * @param enc
	 * @return
	 */
	public static String decode(String content, String enc){
		try {
			return URLDecoder.decode(content, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
	public static boolean ma(String inputStr,String pattern){
		Pattern p = Pattern.compile(pattern);
		return false;
	}
}