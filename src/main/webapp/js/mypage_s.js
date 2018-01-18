/*
var subjects;
document.addEventListener('DOMContentLoaded', function() {
    var subjectsReq = new XMLHttpRequest();
    subjectsReq.onreadystatechange = function() {
        if (subjectsReq.readyState == 4 && subjectsReq.status == 200) {
            subjects = JSON.parse(subjectsReq.responseText);
            var graphTab = document.getElementById('graph-tab');
            var graphTabContents = document.getElementById('graph-tab-contents');
            subjects.forEach(function(e) {
                var button = document.createElement('button');
                button.type = 'button';
                button.classList.add('tab-buttons');
                button.setAttribute('onclick', 'switchTab(this,\'subject-' + e.subject_id + '\')');
                button.textContent = e.subject_name;
                var tabContent = document.createElement('div');
                tabContent.id = 'subject-' + e.subject_id;
                tabContent.classList.add('tab-contents');
                tabContent.innerHTML = '<svg id="result-graph-' + e.subject_id + '" class="result-graph"></svg>' +
                    '<div class="result-info">' +
                    '<p id="test_name-' + e.subject_id + '"></p>' +
                    '<p id="my_point-' + e.subject_id + '"></p>' +
                    '<p id="average_point-' + e.subject_id + '"></p>' +
                    '</div>';
                graphTab.appendChild(button);
                graphTabContents.appendChild(tabContent);
            });
            document.getElementsByClassName('tab-buttons')[0].click();
        }
    };
    subjectsReq.open('GET','api/subjects');
    subjectsReq.send();
});
*/

document.addEventListener('DOMContentLoaded', function() {
    makeResultGraphTabs();

});

function makeResultGraphTabs(){
    d3.json('api/myresult', function(error, data) {
        var graphTab = document.getElementById('graph-tab');
        var graphTabContents = document.getElementById('graph-tab-contents');

        data.forEach(function(el){
            var button = document.createElement('button');
            button.type = 'button';
            button.classList.add('tab-buttons');
            button.setAttribute('onclick', 'switchTab(this,\'subject-' + el.subject_id + '\')');
            button.textContent = el.subject_name;
            graphTab.appendChild(button);

            var tabContent = document.createElement('div');
            tabContent.id = 'subject-' + el.subject_id;
            tabContent.classList.add('tab-contents');
            tabContent.innerHTML = '<svg id="result-graph-' + el.subject_id + '" class="result-graph"></svg>' +
                '<div class="result-info">' +
                '<p id="test_name-' + el.subject_id + '"></p>' +
                '<p id="my_point-' + el.subject_id + '"></p>' +
                '<p id="average_point-' + el.subject_id + '"></p>' +
                '</div>';
            graphTabContents.appendChild(tabContent);
            drawResultGraph(el);
        });
        document.getElementsByClassName('tab-buttons')[0].click();
    });
}

function drawResultGraph(subject){
    var results = subject.results;
    // x軸のスケールを定義
    var x = d3.scaleBand()
        .rangeRound([0, 400])
        .padding(.1)
        .domain(results.map(function(d) {
            return d.test_name;
        }));
    // y軸のスケールを定義
    var y = d3.scaleLinear()
        .range([0, 100])
        .domain([0, 100]);
    // テストごとのグループを作成
    var resultsSelection = d3.select('#result-graph-' + subject.subject_id).selectAll('.results')
        .data(results).enter()
        .append('g')
        .attr('class', 'results')
        .attr('transform', function(d) {
            return 'translate(' + x(d.test_name) + ',0)';
        });
    // ユーザーのテスト結果の棒グラフを描画
    resultsSelection.append('rect')
        .attr('class', 'my-bar')
        .attr('x', 0)
        .attr('width', x.bandwidth())
        .attr('y', 100)
        .attr('height', 0)
        .transition()
        .attr('y', function(d) {
            return 100 - y(d.my_point);
        })
        .attr('height', function(d) {
            return y(d.my_point);
        });
    // 平均のテスト結果を横線で描画
    resultsSelection.append('rect')
        .attr('class', 'average-bar')
        .attr('x', 0)
        .attr('y', function(d) {
            return 100 - y(d.average_point);
        })
        .attr('width', x.bandwidth())
        .attr('height', 1);
    // ユーザーのテスト結果の点数を描画
    resultsSelection.append('text')
        .attr('y', function(d) {
            return 112 - y(d.my_point);
        })
        .text(function(d) {
            return d.my_point;
        });

    // マウスオーバーでテスト名や点数を表示
    resultsSelection.on('mouseover', function(d) {
        d3.select('#test_name-' + subject.subject_id).text(d.test_name);
        d3.select('#my_point-' + subject.subject_id).text('あなたの点数：' + d.my_point);
        d3.select('#average_point-' + subject.subject_id).text('平均点：' + d.average_point);
    });
}
