/**
 * Copyright (c) 2017 ytk-learn https://github.com/yuantiku
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.fenbi.ytklearn.feature.gbdt.approximate.sampler;

import com.fenbi.mp4j.exception.Mp4jException;
import com.fenbi.mp4j.comm.ThreadCommSlave;
import com.fenbi.ytklearn.dataflow.GBDTCoreData;
import com.fenbi.ytklearn.utils.NumConvertUtils;
import com.fenbi.ytklearn.utils.RandomUtils;

import java.util.*;

/**
 * @author wufan
 * @author xialong
 */

public class SampleByRate extends AbstractSampler {

    private float sampleRate;
    // when feature values count > minCnt, we do sample
    private int minCnt;
    private ThreadCommSlave comm;

    public SampleByRate(ThreadCommSlave aggregate) {
        comm = aggregate;
    }


    @Override
    public void init(Map<String, String> params) {
        sampleRate = Float.parseFloat(params.get("sample_rate"));
        minCnt = Integer.parseInt(params.get("min_cnt"));
    }

    @Override
    public Object doSample(GBDTCoreData data, int fid) throws Mp4jException {
        HashSet<Float> feaVals = new HashSet<>();
        for (int i = 0; i < data.sampleNum; i++) {
            feaVals.add(NumConvertUtils.int2float(data.getFeatureVal(i, fid)));
            if (feaVals.size() > minCnt) {
                break;
            }
        }

        if (feaVals.size() > minCnt) {
            feaVals.clear();
            Random rand = new Random();
            for (int i = 0; i < data.sampleNum; i++) {
                if (RandomUtils.sampleBinary(rand, sampleRate)) {
                    feaVals.add(NumConvertUtils.int2float(data.getFeatureVal(i, fid)));
                }
            }
        }
        data.LOG_UTILS.verboseInfo(String.format("SampleByRate(sample_rate=%f, min_cnt=%d), sample out %d values", sampleRate, minCnt, feaVals.size()), false);
        return feaVals;
    }
}
