graph [
directed 1
node [
name "org.reclipse.test.pooldetectiontest.TestWorkerThread"
label "org.reclipse.test.pooldetectiontest.TestWorkerThread"
graphics
[hasFill 0
]
]
node [
name "org.reclipse.test.pooldetectiontest.ThreadPool"
label "org.reclipse.test.pooldetectiontest.ThreadPool"
graphics
[hasFill 0
]
]
node [
name "org.reclipse.test.pooldetectiontest.Done"
label "org.reclipse.test.pooldetectiontest.Done"
graphics
[hasFill 0
]
]
node [
name "org.reclipse.test.pooldetectiontest.TestThreadPool"
label "org.reclipse.test.pooldetectiontest.TestThreadPool"
graphics
[hasFill 0
]
]
node [
name "org.reclipse.test.pooldetectiontest.WorkerThread"
label "org.reclipse.test.pooldetectiontest.WorkerThread"
graphics
[hasFill 0
]
]
edge [
source "org.reclipse.test.pooldetectiontest.TestWorkerThread"
target "org.reclipse.test.pooldetectiontest.Done"
label "From: TestWorkerThread To: Done Count: 1"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.ThreadPool"
target "org.reclipse.test.pooldetectiontest.Done"
label "From: ThreadPool To: Done Count: 9"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.ThreadPool"
target "org.reclipse.test.pooldetectiontest.WorkerThread"
label "From: ThreadPool To: WorkerThread Count: 1"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.TestThreadPool"
target "org.reclipse.test.pooldetectiontest.ThreadPool"
label "From: TestThreadPool To: ThreadPool Count: 4"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.TestThreadPool"
target "org.reclipse.test.pooldetectiontest.TestWorkerThread"
label "From: TestThreadPool To: TestWorkerThread Count: 1"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.WorkerThread"
target "org.reclipse.test.pooldetectiontest.ThreadPool"
label "From: WorkerThread To: ThreadPool Count: 4"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.reclipse.test.pooldetectiontest.WorkerThread"
target "org.reclipse.test.pooldetectiontest.Done"
label "From: WorkerThread To: Done Count: 1"
graphics
[
targetArrow "standard"
]
]
]
