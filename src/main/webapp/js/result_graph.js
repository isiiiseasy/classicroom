//--------------------テスト結果グラフ--------------------
d3.csv('my_results.csv', function(error, results) {
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
    var resultsSelection = d3.select('#result-graph').selectAll('.results')
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
        d3.select('#test_name').text(d.test_name);
        d3.select('#my_point').text('あなたの点数：' + d.my_point);
        d3.select('#average_point').text('平均点：' + d.average_point);
    });
});
