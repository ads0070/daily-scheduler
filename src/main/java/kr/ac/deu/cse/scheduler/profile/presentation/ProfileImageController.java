package kr.ac.deu.cse.scheduler.profile.presentation;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.ac.deu.cse.scheduler.profile.application.ProfileService;

@Controller
public class ProfileImageController {
    
	private final ProfileService profileService;
    private static final Logger logger = LoggerFactory.getLogger(ProfileImageController.class);
    
    @Autowired
    public ProfileImageController (ProfileService profileService) {
    	this.profileService = profileService;
    }
    
    @RequestMapping(value = "/fileDownload/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> readOne(@PathVariable final String username) {
        String path = profileService.getProfileByUsername(username);
        return ResponseEntity.ok(path);
    }
    
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map fileUpload(HttpServletRequest req, HttpServletResponse rep, @RequestParam Map<String, String> paramMap) { 
    	String username = paramMap.get("username");
    	System.out.println(username);
        // String path = req.getSession().getServletContext().getRealPath("") + "\\resources";
        // String path = req.getSession().getServletContext().getRealPath("/resources/");
        String path = "C:\\profile\\";
    	String originName = "";
    	String saveFileName = "";
    	
        System.out.println("path : " + path);
        
        Map returnObject = new HashMap(); 
        try { 
            MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
            Iterator iter = mhsr.getFileNames(); 
            MultipartFile mfile = null; 
            String fieldName = ""; 
            List resultList = new ArrayList(); 
            
            File dir = new File(path); 
            if (!dir.isDirectory()) { 
                dir.mkdirs(); 
            } 
            
            while (iter.hasNext()) { 
                fieldName = (String) iter.next();
                mfile = mhsr.getFile(fieldName);
                originName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8");
                
                System.out.println("originName: " + originName);
                
                if ("".equals(originName)) {
                    continue; 
                } 
                
                saveFileName = originName;
                
                System.out.println("saveFileName : " + saveFileName);
                
                File serverFile = new File(path + File.separator + saveFileName);
                mfile.transferTo(serverFile);
                
                Map file = new HashMap();
                file.put("originName", originName); file.put("sfile", serverFile);
                resultList.add(file);
            }
            
            returnObject.put("files", resultList); 
            returnObject.put("params", mhsr.getParameterMap()); 
            } catch (UnsupportedEncodingException e) { 
                e.printStackTrace(); 
            }catch (IllegalStateException e) {  
                e.printStackTrace();
            } catch (IOException e) { 
                e.printStackTrace();
            }
        	profileService.createProfile(username, path, originName, saveFileName);
            return null;
    }
    
    
}