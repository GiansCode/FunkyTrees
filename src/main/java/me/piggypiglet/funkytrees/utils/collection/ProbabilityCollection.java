/*
 * Copyright (c) 2020 Lewys Davies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package me.piggypiglet.funkytrees.utils.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class ProbabilityCollection<E> {
    private final NavigableSet<ProbabilitySetElement<E>> collection =
            new TreeSet<>(Comparator.comparingInt(ProbabilitySetElement::getIndex));
    private final SplittableRandom random = new SplittableRandom();

    private int totalProbability = 0;

    public ProbabilityCollection(@NotNull final Map<E, Integer> map) {
        map.forEach(this::add);
    }

    @NotNull
    public Iterator<ProbabilitySetElement<E>> iterator() {
        return this.collection.iterator();
    }

    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    private void add(@NotNull final E object, final int probability) {
        if (probability <= 0) {
            throw new IllegalArgumentException("Probability must be greater than 0");
        }

        final ProbabilitySetElement<E> entry = new ProbabilitySetElement<>(object, probability);
        entry.setIndex(this.totalProbability + 1);

        this.collection.add(entry);
        this.totalProbability += probability;
    }

    @NotNull
    public E get() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot get an object out of a empty collection");
        }

        final ProbabilitySetElement<E> toFind = new ProbabilitySetElement<>(null, 0);
        toFind.setIndex(this.random.nextInt(1, this.totalProbability + 1));

        return Objects.requireNonNull(Objects.requireNonNull(this.collection.floor(toFind)).getObject());
    }

    /**
     * Used internally to store information about a object's
     * state in a collection. Specifically, the probability
     * and index within the collection.
     * <p>
     * Indexes refer to the start position of this element's "block" of space.
     * The space between element "block"s represents their probability of being selected
     *
     * @param <T> Type of element
     * @author Lewys Davies
     */
    private static final class ProbabilitySetElement<T> {
        private final T object;
        private final int probability;
        private int index;

        protected ProbabilitySetElement(@Nullable final T object, final int probability) {
            this.object = object;
            this.probability = probability;
        }

        @Nullable
        public T getObject() {
            return this.object;
        }

        public int getProbability() {
            return this.probability;
        }

        private int getIndex() {
            return this.index;
        }

        private void setIndex(final int index) {
            this.index = index;
        }
    }
}
