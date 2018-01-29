document.addEventListener('DOMContentLoaded', function() {
    getSecionData();
});

function getSecionData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeSectionList(data);
        }
    };
    xhr.open('GET', 'api/sections');
    xhr.send();
}

function makeSectionList(data) {
    var sectionList = document.getElementById('section-list');
    for (var subject of data) {
        var DOMSubject = document.createElement('li');
        var DOMChapterList = document.createElement('ol');
        DOMSubject.textContent = subject.subject_name;
        var DOMSubjectEdit = document.createElement('a');
        DOMSubjectEdit.textContent = ' [名前の変更]';
        DOMSubject.appendChild(DOMSubjectEdit);
        var DOMSubjectDelete = document.createElement('a');
        DOMSubjectDelete.textContent = ' [削除]';
        DOMSubject.appendChild(DOMSubjectDelete);
        for (var chapter of subject.chapters || []) {
            var DOMChapter = document.createElement('li');
            var DOMSectionList = document.createElement('ol');
            DOMChapter.textContent = chapter.chapter_name;
            var DOMChapterEdit = document.createElement('a');
            DOMChapterEdit.textContent = ' [名前の変更]';
            DOMChapter.appendChild(DOMChapterEdit);
            var DOMChapterDelete = document.createElement('a');
            DOMChapterDelete.textContent = ' [削除]';
            DOMChapter.appendChild(DOMChapterDelete);
            for (var section of chapter.sections || []) {
                var DOMSection = document.createElement('li');
                DOMSection.textContent = section.section_name;
                var DOMSectionEdit = document.createElement('a');
                DOMSectionEdit.textContent = ' [名前の変更]';
                DOMSection.appendChild(DOMSectionEdit);
                var DOMSectionDelete = document.createElement('a');
                DOMSectionDelete.textContent = ' [削除]';
                DOMSection.appendChild(DOMSectionDelete);
                DOMSectionList.appendChild(DOMSection);
            }
            var DOMSectionAdd = document.createElement('li');
            DOMSectionAdd.textContent = '項目を追加';
            DOMSectionList.appendChild(DOMSectionAdd);
            DOMChapter.appendChild(DOMSectionList);
            DOMChapterList.appendChild(DOMChapter);
        }
        var DOMChapterAdd = document.createElement('li');
        DOMChapterAdd.textContent = '章を追加';
        DOMChapterList.appendChild(DOMChapterAdd);
        DOMSubject.appendChild(DOMChapterList);
        sectionList.appendChild(DOMSubject);
    }
    var DOMSubjectAdd = document.createElement('li');
    DOMSubjectAdd.textContent = '教科を追加';
    sectionList.appendChild(DOMSubjectAdd);
}
