package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.shared.dto.FilesystemLimitsDto;

import java.util.List;

public interface FilesystemLimitService {
    List<FilesystemLimitsDto> getFilesystemLimits();
}
