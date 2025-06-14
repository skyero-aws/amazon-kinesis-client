/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates.
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package software.amazon.kinesis.retrieval;

import software.amazon.awssdk.services.kinesis.model.GetRecordsResponse;

/**
 * Represents the result from the DataFetcher, and allows the receiver to accept a result
 */
public interface DataFetcherResult {
    /**
     * The result of the request to Kinesis
     *
     * @return The result of the request, as a {@link GetRecordsResponseAdapter}.
     */
    default GetRecordsResponseAdapter getResultAdapter() {
        return new KinesisGetRecordsResponseAdapter(getResult());
    }

    /**
     * The result of the request to Kinesis
     *
     * @return The result of the request, this can be null if the request failed.
     */
    GetRecordsResponse getResult();

    /**
     * Accepts the result, and advances the shard iterator. A result from the data fetcher must be accepted before any
     * further progress can be made.
     *
     * @return the result of the request as a {@link GetRecordsResponseAdapter}
     */
    default GetRecordsResponseAdapter acceptAdapter() {
        return new KinesisGetRecordsResponseAdapter(accept());
    }

    /**
     * Accepts the result, and advances the shard iterator. A result from the data fetcher must be accepted before any
     * further progress can be made.
     *
     * @return the result of the request, this can be null if the request failed.
     */
    GetRecordsResponse accept();

    /**
     * Indicates whether this result is at the end of the shard or not
     *
     * @return true if the result is at the end of a shard, false otherwise
     */
    boolean isShardEnd();
}
