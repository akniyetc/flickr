package com.silence.flickr.common.functional

sealed class Failure : RuntimeException() {
    class NetworkConnection : Failure()
    class ServerError : Failure()

    abstract class FeatureFailure : Failure()
}