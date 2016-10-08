/*!
 * FileInput Chinese Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 * @author kangqf <kangqingfei@gmail.com>
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";

    $.fn.fileinputLocales['zh'] = {
        fileSingle: '�ļ�',
        filePlural: '����ļ�',
        browseLabel: 'ѡ�� &hellip;',
        removeLabel: '�Ƴ�',
        removeTitle: '���ѡ���ļ�',
        cancelLabel: 'ȡ��',
        cancelTitle: 'ȡ�������е��ϴ�',
        uploadLabel: '�ϴ�',
        uploadTitle: '�ϴ�ѡ���ļ�',
        msgNo: 'û��',
        msgNoFilesSelected: '',
        msgCancelled: 'ȡ��',
        msgZoomModalHeading: '��ϸԤ��',
        msgSizeTooLarge: '�ļ� "{name}" (<b>{size} KB</b>) ������������С <b>{maxSize} KB</b>.',
        msgFilesTooLess: '�����ѡ������ <b>{n}</b> {files} ���ϴ�. ',
        msgFilesTooMany: 'ѡ����ϴ��ļ����� <b>({n})</b> ��������ļ������Ƹ��� <b>{m}</b>.',
        msgFileNotFound: '�ļ� "{name}" δ�ҵ�!',
        msgFileSecured: '��ȫ���ƣ�Ϊ�˷�ֹ��ȡ�ļ� "{name}".',
        msgFileNotReadable: '�ļ� "{name}" ���ɶ�.',
        msgFilePreviewAborted: 'ȡ�� "{name}" ��Ԥ��.',
        msgFilePreviewError: '��ȡ "{name}" ʱ������һ������.',
        msgInvalidFileType: '����ȷ������ "{name}". ֻ֧�� "{types}" ���͵��ļ�.',
        msgInvalidFileExtension: '����ȷ���ļ���չ�� "{name}". ֻ֧�� "{extensions}" ���ļ���չ��.',
        msgUploadAborted: '���ļ��ϴ�����ֹ',
        msgUploadThreshold: 'Processing...',
        msgValidationError: '��֤����',
        msgLoading: '���ص� {index} �ļ� �� {files} &hellip;',
        msgProgress: '���ص� {index} �ļ� �� {files} - {name} - {percent}% ���.',
        msgSelected: '{n} {files} ѡ��',
        msgFoldersNotAllowed: 'ֻ֧����ק�ļ�! ���� {n} ��ק���ļ���.',
        msgImageWidthSmall: '���ȵ�ͼ���ļ���"{name}"�ı���������{size}����.',
        msgImageHeightSmall: 'ͼ���ļ���"{name}"�ĸ߶ȱ�������Ϊ{size}����.',
        msgImageWidthLarge: '���ȵ�ͼ���ļ�"{name}"���ܳ���{size}����.',
        msgImageHeightLarge: 'ͼ���ļ�"{name}"�ĸ߶Ȳ��ܳ���{size}����.',
        msgImageResizeError: '�޷���ȡ��ͼ��ߴ������',
        msgImageResizeException: '���������ͼ���С��<pre>{errors}</pre>',
        dropZoneTitle: '��ק�ļ������� &hellip;',
        dropZoneClickTitle: '<br>(or click to select {files})',
        fileActionSettings: {
            removeTitle: 'ɾ���ļ�',
            uploadTitle: '�ϴ��ļ�',
            zoomTitle: '�鿴����',
            dragTitle: 'Move / Rearrange',
            indicatorNewTitle: 'û���ϴ�',
            indicatorSuccessTitle: '�ϴ�',
            indicatorErrorTitle: '�ϴ�����',
            indicatorLoadingTitle: '�ϴ� ...'
        },
        previewZoomButtonTitles: {
            prev: 'View previous file',
            next: 'View next file',
            toggleheader: 'Toggle header',
            fullscreen: 'Toggle full screen',
            borderless: 'Toggle borderless mode',
            close: 'Close detailed preview'
        }
    };
})(window.jQuery);