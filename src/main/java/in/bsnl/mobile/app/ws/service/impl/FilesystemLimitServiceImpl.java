package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.FilesystemLimit;
import in.bsnl.mobile.app.ws.io.repository.FilesystemLimitRepo;
import in.bsnl.mobile.app.ws.service.FilesystemLimitService;
import in.bsnl.mobile.app.ws.shared.dto.FilesystemLimitsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilesystemLimitServiceImpl implements FilesystemLimitService {
    @Autowired
    FilesystemLimitRepo filesystemLimitRepo;


    @Override
    public List<FilesystemLimitsDto> getFilesystemLimits() {
        Iterable<FilesystemLimit> filesystemLimits = filesystemLimitRepo.findAll();
        List<FilesystemLimitsDto> returned = new ArrayList<FilesystemLimitsDto>();
        for(FilesystemLimit filesystemLimit: filesystemLimits){
            FilesystemLimitsDto filesystemLimitsDto = new FilesystemLimitsDto();
            BeanUtils.copyProperties(filesystemLimits,filesystemLimitsDto);
            returned.add(filesystemLimitsDto);
        }
        return returned;
    }
}
