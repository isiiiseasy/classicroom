document.addEventListener('DOMContentLoaded', function() {
    getSectionData();
});

function getSectionData() {
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
    Array.prototype.slice.call(sectionList.children).forEach(function(child) {
        child.parentNode.removeChild(child);
    });
    for (var subject of data) {
        var DOMSubject = document.createElement('li');
        var DOMChapterList = document.createElement('ol');
        DOMSubject.textContent = subject.subject_name;
        var DOMSubjectEdit = document.createElement('a');
        DOMSubjectEdit.textContent = ' [名前の変更]';
        DOMSubjectEdit.addEventListener('click', editSubject.bind(null, subject.subject_id));
        DOMSubject.appendChild(DOMSubjectEdit);
        var DOMSubjectDelete = document.createElement('a');
        DOMSubjectDelete.textContent = ' [削除]';
        DOMSubjectDelete.addEventListener('click', deleteSubject.bind(null, subject.subject_id));
        DOMSubject.appendChild(DOMSubjectDelete);
        for (var chapter of subject.chapters || []) {
            var DOMChapter = document.createElement('li');
            var DOMSectionList = document.createElement('ol');
            DOMChapter.textContent = chapter.chapter_name;
            var DOMChapterEdit = document.createElement('a');
            DOMChapterEdit.textContent = ' [名前の変更]';
            DOMChapterEdit.addEventListener('click', editChapter.bind(null, chapter.chapter_id));
            DOMChapter.appendChild(DOMChapterEdit);
            var DOMChapterDelete = document.createElement('a');
            DOMChapterDelete.textContent = ' [削除]';
            DOMChapterDelete.addEventListener('click', deleteChapter.bind(null, chapter.chapter_id));
            DOMChapter.appendChild(DOMChapterDelete);
            for (var section of chapter.sections || []) {
                var DOMSection = document.createElement('li');
                DOMSection.textContent = section.section_name;
                var DOMSectionEdit = document.createElement('a');
                DOMSectionEdit.textContent = ' [名前の変更]';
                DOMSectionEdit.addEventListener('click', editSection.bind(null, section.section_id));
                DOMSection.appendChild(DOMSectionEdit);
                var DOMSectionDelete = document.createElement('a');
                DOMSectionDelete.textContent = ' [削除]';
                DOMSectionDelete.addEventListener('click', deleteSection.bind(null, section.section_id));
                DOMSection.appendChild(DOMSectionDelete);
                DOMSectionList.appendChild(DOMSection);
            }
            var DOMSectionAdd = document.createElement('li');
            DOMSectionAdd.textContent = '項目を追加';
            DOMSectionAdd.addEventListener('click', addSection.bind(null, chapter.chapter_id));
            DOMSectionList.appendChild(DOMSectionAdd);
            DOMChapter.appendChild(DOMSectionList);
            DOMChapterList.appendChild(DOMChapter);
        }
        var DOMChapterAdd = document.createElement('li');
        DOMChapterAdd.textContent = '章を追加';
        DOMChapterAdd.addEventListener('click', addChapter.bind(null, subject.subject_id));
        DOMChapterList.appendChild(DOMChapterAdd);
        DOMSubject.appendChild(DOMChapterList);
        sectionList.appendChild(DOMSubject);
    }
    var DOMSubjectAdd = document.createElement('li');
    DOMSubjectAdd.textContent = '教科を追加';
    DOMSubjectAdd.addEventListener('click', addSubject);
    sectionList.appendChild(DOMSubjectAdd);
}

function addSection(chapterId) {
    var sectionName = window.prompt('新しい項目名');
    if (sectionName) {
        requestSectionAPI('POST', '&chapter-id=' + encodeURIComponent(chapterId) + '&section-name=' + encodeURIComponent(sectionName));
    } else {
        window.alert('キャンセルされました');
    }
}

function addChapter(subjectId) {
    var chapterName = window.prompt('新しい章名');
    if (chapterName) {
        requestSectionAPI('POST', '&subject-id=' + encodeURIComponent(subjectId) + '&chapter-name=' + encodeURIComponent(chapterName));
    } else {
        window.alert('キャンセルされました');
    }
}

function addSubject() {
    var subjectName = window.prompt('新しい教科名');
    if (subjectName) {
        requestSectionAPI('POST', '&subject-name=' + encodeURIComponent(subjectName));
    } else {
        window.alert('キャンセルされました');
    }
}

function editSection(sectionId) {
    var sectionName = window.prompt('変更後の教科名');
    if (sectionName) {
        requestSectionAPI('PUT', '&section-id=' + encodeURIComponent(sectionId) + '&section-name=' + encodeURIComponent(sectionName));
    } else {
        window.alert('キャンセルされました');
    }
}

function editChapter(chapterId) {
    var chapterName = window.prompt('変更後の章名');
    if (chapterName) {
        requestSectionAPI('PUT', '&chapter-id=' + encodeURIComponent(chapterId) + '&chapter-name=' + encodeURIComponent(chapterName));
    } else {
        window.alert('キャンセルされました');
    }
}

function editSubject(subjectId) {
    var subjectName = window.prompt('変更後の科目名');
    if (subjectName) {
        requestSectionAPI('PUT', '&subject-id=' + encodeURIComponent(subjectId) + '&subject-name=' + encodeURIComponent(subjectName));
    } else {
        window.alert('キャンセルされました');
    }
}

function deleteSection(sectionId) {
    if (window.confirm('本当によろしいですか？')) {
        requestSectionAPI('DELETE', '&section-id=' + encodeURIComponent(sectionId));
    } else {
        window.alert('キャンセルされました');
    }
}

function deleteChapter(chapterId) {
    if (window.confirm('本当によろしいですか？')) {
        requestSectionAPI('DELETE', '&chapter-id=' + encodeURIComponent(chapterId));
    } else {
        window.alert('キャンセルされました');
    }
}

function deleteSubject(subjectId) {
    if (window.confirm('本当によろしいですか？')) {
        requestSectionAPI('DELETE', '&subject-id=' + encodeURIComponent(subjectId));
    } else {
        window.alert('キャンセルされました');
    }
}

function requestSectionAPI(method, param) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeSectionList(data);
        }
    };
    xhr.open(method, 'api/sections?' + param);
    xhr.send();
}
