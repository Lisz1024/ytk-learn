/**
*
* Copyright (c) 2017 ytk-learn https://github.com/yuantiku
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:

* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.

* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package com.fenbi.ytklearn.loss;

/**
 * @author xialong
 */

public class HingeFunction extends AbstractLossFunction {

    @Override
    public final double loss(double score, double label) {
        return Math.max(0.0, 1.0 - ((2 * label - 1.0) * score));
    }

    @Override
    public final double predict(double score) {
        return score;
    }

    @Override
    public final double firstDerivative(double score, double label) {
        double xlabel = 2 * label - 1.0;
        double z = xlabel * score;
        if (z < 1.0) {
            return -xlabel;
        } else {
            return 0.0;
        }
    }

    @Override
    public final double secondDerivative(double score, double label) {
        return 0.0;
    }

    @Override
    public String getName() {
        return "hinge";
    }
}
