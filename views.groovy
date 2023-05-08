def viewsPrefixRnd = '/aws/group/groupfunctions-rnd'

def commonJobNames = ['admin-seedjob']

def viewsStructure = 
[
    [
        name: viewsPrefixRnd + 'RND',
        branchStructure: false,
        apps: [
            ALL:
            [
                pattern: "rnd-.*"
            ]
        ]
    ]
]

def branchBuildStructure = [
    [
        name: 'All',
        patternSuffix: ''
    ],
    [
        name: 'PIPELINE FROM FEATURE',
        patternSuffix: 'feature-build'
    ]
]

for(def topView: viewsStructure) {
    nestedView(topView.name) {
        views {
            for (def appLevel: topView.apps) {
                nestedView (appLevel.key) {
                    views {
                        def listViewsStructure = (topView.branchStructure)? branchBuildStructure : branchBuildStructure.subList(0,1)
                        for(def listViews: listViewsStructure) {
                            listView(listViews.name) {
                                jobs {
                                    commonJobNames.each {jobName ->
                                        name(jobName)
                                    }
                                    if(listViews.patternSuffix != '') {
                                        regex("(?i)" +appLevel.value.pattern + '(' + listViews.patternSuffix + '|build-trigger-custom)')
                                    } else {
                                        regex("(?i)" +appLevel.value.pattern + listViews.patternSuffix)
                                    }
                                }
                                columns {
                                    status()
                                    weather()
                                    name()
                                    lastSuccess()
                                    lastFailure()
                                    lastDuration()
                                    buildButton()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
