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

public class SMAPEFunction extends AbstractLossFunction {
    private final static double MIN_SCORE = 1e-6;

    @Override
    public double loss(double score, double label) {
        return Math.abs(score - label) / ((label + Math.abs(score)) / 2.0);
    }

    @Override
    public double predict(double score) {
        return score;
    }

    @Override
    public double firstDerivative(double score, double label) {
        double deno = (label + Math.abs(score)) / 2.0;
        return (Math.signum(score - label) * deno - 0.5 * Math.signum(score) * Math.abs(score - label)) / (deno * deno);
    }

    @Override
    public double secondDerivative(double score, double label) {
        return 1.0;
    }

    @Override
    public String getName() {
        return "smape";
    }
}
