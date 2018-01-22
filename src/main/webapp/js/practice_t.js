var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
    if (xhr.readyState == 4 && xhr.status == 200) {
        var data = JSON.parse(xhr.responseText);
        if (document.readyState == 'interactive' || document.readyState == 'complete') {
            makeSectionList(data);
        } else {
            document.addEventListener('DOMContentLoaded', function() {
                makeSectionList(data);
            });
        }
    }
};
xhr.open('GET','api/sections');
xhr.send();

function makeSectionList(data) {
    var sectionList = document.getElementById('section-list');
    for (var subject of data) {
        var DOMSubject = document.createElement('li');
        var DOMChapterList = document.createElement('ol');
        DOMSubject.textContent = subject.subject_name;
        for (var chapter of subject.chapters ||[]) {
            var DOMChapter = document.createElement('li');
            var DOMSectionList = document.createElement('ol');
            DOMChapter.textContent = chapter.chapter_name;
            for (var section of chapter.sections||[]) {
                var DOMSection = document.createElement('li');
                DOMSection.textContent = section.section_name;
                DOMSectionList.appendChild(DOMSection);
            }
            DOMChapter.appendChild(DOMSectionList);
            DOMChapterList.appendChild(DOMChapter);
        }
        DOMSubject.appendChild(DOMChapterList);
        sectionList.appendChild(DOMSubject);
    }
}
