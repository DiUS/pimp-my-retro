app.filter('topicCategory', function() {
    return function(items, category) {

        var filtered = new Array();

        angular.forEach(items, function(topic) {
            if(topic.category) {
                if(topic.category.title == category.title) {
                    filtered.push(topic);
                }
            }
        });

        return filtered;
    };
});
