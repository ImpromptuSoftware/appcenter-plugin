package io.jenkins.plugins.appcenter.util;

import hudson.FilePath;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static com.google.common.truth.Truth.assertThat;
import static io.jenkins.plugins.appcenter.util.TestFileUtil.TEST_FILE_PATH;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RemoteFileUtilsTest {

    @Mock
    FilePath filePath;

    private RemoteFileUtils remoteFileUtils;

    @Before
    public void setUp() {
        given(filePath.child(anyString())).willReturn(filePath);
        remoteFileUtils = new RemoteFileUtils(filePath);
    }

    @Test
    public void should_ReturnFile_When_FileExists() {
        // Given
        final File expected = new File(TEST_FILE_PATH);
        given(filePath.getRemote()).willReturn(TEST_FILE_PATH);

        // When
        final File remoteFile = remoteFileUtils.getRemoteFile(TEST_FILE_PATH);

        // Then
        assertThat(remoteFile).isEqualTo(expected);
    }

    @Test
    public void should_ReturnDifferentFiles_When_MultipleFileExists() {
        // Given
        final String TEST_PATH_2 = TEST_FILE_PATH+"2";
        final File expected = new File(TEST_FILE_PATH);
        final File expected2 = new File(TEST_PATH_2);
        given(filePath.getRemote()).willReturn(TEST_FILE_PATH);

        // When
        final File remoteFile = remoteFileUtils.getRemoteFile(TEST_FILE_PATH);

        // Then
        assertThat(remoteFile).isEqualTo(expected);

        // Given
        given(filePath.getRemote()).willReturn(TEST_PATH_2);
        // When
        final File remoteFile2 = remoteFileUtils.getRemoteFile(TEST_PATH_2);
        // Then
        assertThat(remoteFile2).isEqualTo(expected2);

        // When
        final File firstFile = remoteFileUtils.getRemoteFile(TEST_FILE_PATH);
        // Then
        assertThat(firstFile).isEqualTo(expected);
    }

    @Test
    public void should_ReturnFileName_When_FileExists() {
        // Given
        given(filePath.getRemote()).willReturn(TEST_FILE_PATH);

        // When
        final String fileName = remoteFileUtils.getFileName(TEST_FILE_PATH);

        // Then
        assertThat(fileName).isEqualTo("xiola.apk");
    }

    @Test
    public void should_ReturnFileSize_When_FileExists() {
        // Given
        given(filePath.getRemote()).willReturn(TEST_FILE_PATH);

        // When
        final long fileSize = remoteFileUtils.getFileSize(TEST_FILE_PATH);

        // Then
        // Windows reports the file size differently so for the sake of simplicity we adjust our assertion here.
        assertThat(fileSize).isEqualTo(SystemUtils.IS_OS_UNIX ? 41 : 42);
    }

    @Test
    public void should_ReturnContentType_When_APK() {
        // Given
        final String pathToFile = "test.apk";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/vnd.android.package-archive");
    }

    @Test
    public void should_ReturnContentType_When_AAB() {
        // Given
        final String pathToFile = "test.aab";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/vnd.android.package-archive");
    }

    @Test
    public void should_ReturnContentType_When_MSI() {
        // Given
        final String pathToFile = "test.msi";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-msi");
    }

    @Test
    public void should_ReturnContentType_When_PLIST() {
        // Given
        final String pathToFile = "test.plist";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/xml");
    }

    @Test
    public void should_ReturnContentType_When_AETX() {
        // Given
        final String pathToFile = "test.aetx";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/c-x509-ca-cert");
    }

    @Test
    public void should_ReturnContentType_When_CER() {
        // Given
        final String pathToFile = "test.cer";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/pkix-cert");
    }

    @Test
    public void should_ReturnContentType_When_XAP() {
        // Given
        final String pathToFile = "test.xap";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-silverlight-app");
    }

    @Test
    public void should_ReturnContentType_When_APPX() {
        // Given
        final String pathToFile = "test.appx";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-appx");
    }

    @Test
    public void should_ReturnContentType_When_APPXBUNDLE() {
        // Given
        final String pathToFile = "test.appxbundle";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-appxbundle");
    }

    @Test
    public void should_ReturnContentType_When_APPXUPLOAD() {
        // Given
        final String pathToFile = "test.appxupload";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-appxupload");
    }

    @Test
    public void should_ReturnContentType_When_APPXSYM() {
        // Given
        final String pathToFile = "test.appxsym";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-appxupload");
    }

    @Test
    public void should_ReturnContentType_When_MSIX() {
        // Given
        final String pathToFile = "test.msix";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-msix");
    }

    @Test
    public void should_ReturnContentType_When_MSIXBUNDLE() {
        // Given
        final String pathToFile = "test.msixbundle";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-msixbundle");
    }

    @Test
    public void should_ReturnContentType_When_MSIXUPLOAD() {
        // Given
        final String pathToFile = "test.msixupload";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-msixupload");
    }

    @Test
    public void should_ReturnContentType_When_MSIXSYM() {
        // Given
        final String pathToFile = "test.msixsym";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/x-msixupload");
    }

    @Test
    public void should_ReturnContentType_When_UNKNOWN() {
        // Given
        final String pathToFile = "test.foo";

        // When
        final String contentType = remoteFileUtils.getContentType(pathToFile);

        // Then
        assertThat(contentType).isEqualTo("application/octet-stream");
    }
}